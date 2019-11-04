package com.subscriber.converters;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.models.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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
