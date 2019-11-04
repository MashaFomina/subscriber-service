package com.subscriber.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.subscriber.entities.SmsEntity;
import com.subscriber.models.Sms;

@Component
@RequiredArgsConstructor
public class SmsEntityToSmsConverter implements Converter<SmsEntity, Sms> {

    @Override
    public Sms convert(SmsEntity smsEntity) {
        return Sms.builder()
                .id(smsEntity.getId())
                .datetime(smsEntity.getDatetime())
                .size(smsEntity.getSize())
                .text(smsEntity.getText())
                .sender(SubscriberEntityToSubscriberConverter.convertBase(smsEntity.getSender()))
                .receiver(SubscriberEntityToSubscriberConverter.convertBase(smsEntity.getReceiver()))
                .receiverPhone(smsEntity.getReceiverPhone())
                .build();
    }
}
