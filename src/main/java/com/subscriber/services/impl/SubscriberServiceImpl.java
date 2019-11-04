package com.subscriber.services.impl;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.models.Balance;
import com.subscriber.models.Subscriber;
import com.subscriber.repositories.SubscriberRepository;
import com.subscriber.services.SubscriberService;
import com.subscriber.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {
    private final ConversionService subscriberConversionService;
    private final SubscriberRepository subscriberRepository;

    @Override
    public List<Subscriber> getAllSubscribers() throws Exception {
        return Helper.convertList(subscriberRepository.findAll(), subscriberConversionService, SubscriberEntity.class, Subscriber.class);
    }

    @Override
    public Optional<Subscriber> getSubscriberById(long id) {
        return Helper.execFunctionAndConvert(subscriberRepository::findById, id, subscriberConversionService, Subscriber.class);
    }

    @Override
    public SubscriberEntity getSubscriberEntityByPhone(String phone) throws Exception {
        Optional<SubscriberEntity> subscriber = subscriberRepository.findByPhone(phone.trim());
        if (!subscriber.isPresent()) {
            log.warn("Subscriber not found by phone number.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscriber not found by phone number.");
        }
        return subscriber.get();
    }

    @Override
    public SubscriberEntity getSubscriberEntityByPhoneQuiet(String phone) throws Exception {
        try {
            return getSubscriberEntityByPhone(phone);
        } catch (ResponseStatusException ex) {
            if (!ex.getStatus().equals(HttpStatus.NOT_FOUND)) {
                throw ex;
            }
        }
        return null;
    }

    @Override
    public Optional<Balance> getBalanceById(long id) {
        return Helper.execFunctionAndConvert(subscriberRepository::findById, id, subscriberConversionService, Balance.class);
    }

    @Override
    public Optional<Balance> getBalanceByPhone(String phone) {
        return Helper.execFunctionAndConvert(subscriberRepository::findByPhone, phone, subscriberConversionService, Balance.class);
    }

    @Override
    public SubscriberEntity save(SubscriberEntity subscriber) throws Exception {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public SubscriberEntity changeBalance(SubscriberEntity subscriber, float money, boolean isAdding) throws Exception {
        subscriber.setBalance(isAdding
                ? (subscriber.getBalance() + money)
                : (subscriber.getBalance() - money)
        );
        return subscriberRepository.save(subscriber);
    }

    @Override
    public Balance addMoney(String phone, float money) throws Exception {
        log.info("Start add money to " + phone + ": " + money);

        SubscriberEntity subscriberEntity = getSubscriberEntityByPhone(phone);
        subscriberEntity = changeBalance(subscriberEntity, money, true);

        log.info("The money ({}) added on balance of: {}", money, subscriberEntity.getId());
        return subscriberConversionService.convert(subscriberEntity, Balance.class);
    }

    @Override
    public void deleteAll() {
        subscriberRepository.deleteAll();
    }
}