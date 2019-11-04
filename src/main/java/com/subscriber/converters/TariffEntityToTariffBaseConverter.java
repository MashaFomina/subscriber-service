package com.subscriber.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.subscriber.entities.TariffEntity;
import com.subscriber.models.TariffBase;

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
