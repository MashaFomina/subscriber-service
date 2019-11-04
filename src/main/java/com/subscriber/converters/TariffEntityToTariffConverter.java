package com.subscriber.converters;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.entities.TariffEntity;
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
public class TariffEntityToTariffConverter implements Converter<TariffEntity, Tariff> {

    @Override
    public Tariff convert(TariffEntity tariffEntity) {
        System.out.println("Try to convert: " + tariffEntity);
        return Tariff.builder()
                .id(tariffEntity.getId())
                .name(tariffEntity.getName())
                .priceCall(tariffEntity.getPriceCall())
                .priceSms(tariffEntity.getPriceSms())
                .limitDayCalls(tariffEntity.getLimitDayCalls())
                .oneSmsSize(tariffEntity.getOneSmsSize())
                .createdAt(tariffEntity.getCreatedAt())
                .subscribers(getSubscribers(tariffEntity.getSubscribers()))
                .build();
    }

    private List<SubscriberBase> getSubscribers(List<SubscriberEntity> subscribers) {
        System.out.println("Try to convert subscribers: " + subscribers);
        if (subscribers == null) {
            throw new IllegalArgumentException("Subscribers not set.");
        }
        return subscribers.stream()
                .map(x -> SubscriberEntityToSubscriberConverter.convertBase(x))
                .collect(Collectors.toList());
    }
}
