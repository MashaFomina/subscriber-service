package com.subscriber.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.models.Balance;

@Component
@RequiredArgsConstructor
public class SubscriberEntityToBalanceConverter implements Converter<SubscriberEntity, Balance> {

    @Override
    public Balance convert(SubscriberEntity subscriberEntity) {
        return Balance.builder()
                .balance(subscriberEntity.getBalance())
                .build();
    }
}
