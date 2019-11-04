package com.subscriber.services.impl;

import com.subscriber.entities.CallEntity;
import com.subscriber.entities.SmsEntity;
import com.subscriber.entities.SubscriberEntity;
import com.subscriber.models.Balance;
import com.subscriber.models.Call;
import com.subscriber.models.Sms;
import com.subscriber.repositories.CallRepository;
import com.subscriber.repositories.SmsRepository;
import com.subscriber.repositories.SubscriberRepository;
import com.subscriber.services.CallService;
import com.subscriber.services.SmsService;
import com.subscriber.services.SubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {
    private final ConversionService subscriberConversionService;
    private final CallRepository callRepository;
    private final SubscriberService subscriberService;

    public Call makeCall(String callerPhone, String receiverPhone, float duration) throws Exception {
        log.info("Start making call from {} to {}", callerPhone, receiverPhone);

        SubscriberEntity callerEntity = subscriberService.getSubscriberEntityByPhone(callerPhone);
        List<CallEntity> calls =
                callRepository.findAllWithDateTimeAfter(LocalDateTime.now().minusDays(1), callerEntity.getId());

        short dayLimit = callerEntity.getTariff().getLimitDayCalls();
        if (calls.size() > dayLimit) {
            throw new ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "You exceeded you limit on calls per day: " + dayLimit);
        }

        float callPrice = duration * callerEntity.getTariff().getPriceCall();
        if (callPrice > callerEntity.getBalance()) {
            throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "Not enough money to make call.");
        }

        callerEntity = subscriberService.changeBalance(callerEntity, callPrice, false);
        SubscriberEntity receiver = subscriberService.getSubscriberEntityByPhoneQuiet(receiverPhone);

        CallEntity call = CallEntity.builder()
                .duration(duration)
                .caller(callerEntity)
                .receiver(receiver)
                .receiverPhone(receiverPhone)
                .datetime(LocalDateTime.now())
                .build();
        call = callRepository.save(call);

        log.info("The call was made successfully from {} to {}", callerPhone, receiverPhone);
        return subscriberConversionService.convert(call, Call.class);
    }
}

