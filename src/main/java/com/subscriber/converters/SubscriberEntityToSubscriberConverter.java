package com.subscriber.converters;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.models.Subscriber;
import com.subscriber.models.SubscriberBase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriberEntityToSubscriberConverter implements Converter<SubscriberEntity, Subscriber> {

    private final TariffEntityToTariffConverter tariffEntityToTariffConverter;

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
                .tariff(tariffEntityToTariffConverter.convert(subscriberEntity.getTariff()))
                .build();
    }

    public static SubscriberBase convertBase(SubscriberEntity subscriberEntity) {
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
