package com.subscriber.services.impl;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.entities.TariffEntity;
import com.subscriber.models.Subscriber;
import com.subscriber.models.Tariff;
import com.subscriber.repositories.SubscriberRepository;
import com.subscriber.repositories.TariffRepository;
import com.subscriber.services.SubscriberService;
import com.subscriber.services.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {
    private final ConversionService subscriberConversionService;
    private final TariffRepository tariffRepository;

    @Override
    public List<Tariff> getAllTariffs() throws Exception {
        List<TariffEntity> tariffEntities = tariffRepository.findAll();
        System.out.println(tariffEntities);
        return tariffEntities.stream()
                .map(entity -> subscriberConversionService.convert(entity, Tariff.class))
                .collect(Collectors.toList());
    }
}
