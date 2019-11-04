package com.subscriber.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import com.subscriber.entities.SmsEntity;
import com.subscriber.entities.SubscriberEntity;
import com.subscriber.models.Sms;
import com.subscriber.repositories.SmsRepository;
import com.subscriber.services.SmsService;
import com.subscriber.services.SubscriberService;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final ConversionService subscriberConversionService;
    private final SmsRepository smsRepository;
    private final SubscriberService subscriberService;

    public Sms sendSms(String senderPhone, String receiverPhone, String text) throws Exception {
        log.info("Start make call from {} to {}", senderPhone, receiverPhone);
        SubscriberEntity senderEntity = subscriberService.getSubscriberEntityByPhone(senderPhone);
        short size = (short) text.length();
        int smsCount = (int) Math.ceil(((float) size) / senderEntity.getTariff().getOneSmsSize());
        float smsPrice = smsCount * senderEntity.getTariff().getPriceSms();

        if (smsPrice > senderEntity.getBalance()) {
            throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "Not enough money to send SMS.");
        }

        senderEntity = subscriberService.changeBalance(senderEntity, smsPrice, false);
        SubscriberEntity receiver = subscriberService.getSubscriberEntityByPhoneQuiet(receiverPhone);

        SmsEntity sms = SmsEntity.builder()
                .text(text)
                .sender(senderEntity)
                .receiver(receiver)
                .receiverPhone(receiverPhone)
                .datetime(LocalDateTime.now())
                .size(size)
                .build();
        sms = smsRepository.save(sms);

        log.info("The call was made successfully from {} to {}", senderPhone, receiverPhone);
        return subscriberConversionService.convert(sms, Sms.class);
    }
}
