package com.subscriber.config;


import com.subscriber.converters.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ConvertersConfig {

    private final SubscriberEntityToSubscriberConverter subscriberEntityToSubscriberConverter;
    private final SubscriberEntityToBalanceConverter subscriberEntityToBalanceConverter;
    private final TariffEntityToTariffConverter tariffEntityToTariffConverter;

    @Bean
    public ConversionService subscriberConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(new HashSet<>(Arrays.asList(
                subscriberEntityToSubscriberConverter,
                subscriberEntityToBalanceConverter,
                tariffEntityToTariffConverter
        )));
        bean.afterPropertiesSet();
        return bean.getObject();
    }
}
