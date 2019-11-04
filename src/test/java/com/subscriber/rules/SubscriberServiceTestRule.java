package com.subscriber.rules;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;

import com.subscriber.repositories.TariffRepository;
import com.subscriber.services.SubscriberService;

@NoArgsConstructor
@AllArgsConstructor
public class SubscriberServiceTestRule extends ExternalResource {

    @Autowired
    protected SubscriberService subscriberService;

    @Autowired
    protected TariffRepository tariffRepository;

    @Override
    protected void after() {
        subscriberService.deleteAll();
        tariffRepository.deleteAll();
    }
}
