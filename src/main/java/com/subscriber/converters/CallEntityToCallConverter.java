package com.subscriber.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.subscriber.entities.CallEntity;
import com.subscriber.models.*;

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
