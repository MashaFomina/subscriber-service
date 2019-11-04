package com.subscriber.services;

import com.subscriber.models.Tariff;

import java.util.List;

public interface TariffService {
    /**
     * Gets all tariffs
     *
     * @return list of found tariffs
     */
    List<Tariff> getAllTariffs() throws Exception;
}
