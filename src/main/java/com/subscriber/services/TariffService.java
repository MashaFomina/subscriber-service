package com.subscriber.services;

import com.subscriber.models.Tariff;

import java.util.List;

public interface TariffService {
    List<Tariff> getAllTariffs() throws Exception;
}
