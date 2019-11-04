package com.subscriber.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.models.Subscriber;
import com.subscriber.models.SubscriberBase;

@Component
@RequiredArgsConstructor
public class SubscriberEntityToSubscriberConverter implements Converter<SubscriberEntity, Subscriber> {

    private final TariffEntityToTariffBaseConverter tariffEntityToTariffBaseConverter;

    @Override
    public Subscriber convert(SubscriberEntity subscriberEntity) {
        return Subscriber.builder()
                .id(subscriberEntity.getId())
                .name(subscriberEntity.getName())
                .surname(subscriberEntity.getSurname())
                .password("")
                .phone(subscriberEntity.getPhone())
                .balance(subscriberEntity.getBalance())
                .createdAt(subscriberEntity.getCreatedAt())
                .tariff(tariffEntityToTariffBaseConverter.convert(subscriberEntity.getTariff()))
                .build();
    }

    public static SubscriberBase convertBase(SubscriberEntity subscriberEntity) {
        if (subscriberEntity == null) return new SubscriberBase();
        return SubscriberBase.builder()
                .id(subscriberEntity.getId())
                .name(subscriberEntity.getName())
                .surname(subscriberEntity.getSurname())
                .password("")
                .phone(subscriberEntity.getPhone())
                .balance(subscriberEntity.getBalance())
                .createdAt(subscriberEntity.getCreatedAt())
                .build();
    }
}
