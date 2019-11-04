package com.subscriber.converters;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.entities.TariffEntity;
import com.subscriber.models.SubscriberBase;
import com.subscriber.models.Tariff;
import com.subscriber.models.TariffBase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TariffEntityToTariffBaseConverter implements Converter<TariffEntity, TariffBase> {

    @Override
    public TariffBase convert(TariffEntity tariffEntity) {
        return TariffBase.builder()
                .id(tariffEntity.getId())
                .name(tariffEntity.getName())
                .priceCall(tariffEntity.getPriceCall())
                .priceSms(tariffEntity.getPriceSms())
                .limitDayCalls(tariffEntity.getLimitDayCalls())
                .oneSmsSize(tariffEntity.getOneSmsSize())
                .createdAt(tariffEntity.getCreatedAt())
                .build();
    }
}
