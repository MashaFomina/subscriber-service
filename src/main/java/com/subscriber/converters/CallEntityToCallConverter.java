package com.subscriber.converters;

import com.subscriber.entities.CallEntity;
import com.subscriber.entities.SmsEntity;
import com.subscriber.entities.SubscriberEntity;
import com.subscriber.entities.TariffEntity;
import com.subscriber.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CallEntityToCallConverter implements Converter<CallEntity, Call> {

    @Override
    public Call convert(CallEntity callEntity) {
        return Call.builder()
                .id(callEntity.getId())
                .datetime(callEntity.getDatetime())
                .duration(callEntity.getDuration())
                .caller(SubscriberEntityToSubscriberConverter.convertBase(callEntity.getCaller()))
                .receiver(SubscriberEntityToSubscriberConverter.convertBase(callEntity.getReceiver()))
                .receiverPhone(callEntity.getReceiverPhone())
                .build();
    }
}
