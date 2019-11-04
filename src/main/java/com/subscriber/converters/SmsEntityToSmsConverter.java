package com.subscriber.converters;

import com.subscriber.entities.SmsEntity;
import com.subscriber.entities.SubscriberEntity;
import com.subscriber.entities.TariffEntity;
import com.subscriber.models.Sms;
import com.subscriber.models.Subscriber;
import com.subscriber.models.SubscriberBase;
import com.subscriber.models.Tariff;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
