package com.subscriber.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.HashSet;

import com.subscriber.converters.*;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ConvertersConfig {

    private final SubscriberEntityToSubscriberConverter subscriberEntityToSubscriberConverter;
    private final SubscriberEntityToBalanceConverter subscriberEntityToBalanceConverter;
    private final TariffEntityToTariffConverter tariffEntityToTariffConverter;
    private final CallEntityToCallConverter callEntityToCallConverter;
    private final SmsEntityToSmsConverter smsEntityToSmsConverter;

    @Bean
    public ConversionService subscriberConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(new HashSet<>(Arrays.asList(
                subscriberEntityToSubscriberConverter,
                subscriberEntityToBalanceConverter,
                tariffEntityToTariffConverter,
                callEntityToCallConverter,
                smsEntityToSmsConverter
        )));
        bean.afterPropertiesSet();
        return bean.getObject();
    }
}
