package com.subscriber.rules.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.subscriber.SubscriberApp;
import com.subscriber.entities.SubscriberEntity;
import com.subscriber.entities.TariffEntity;
import com.subscriber.models.Subscriber;
import com.subscriber.repositories.TariffRepository;
import com.subscriber.rules.SubscriberServiceTestRule;
import com.subscriber.services.SubscriberService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {SubscriberApp.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class SubscriberServiceTest {

    protected static final String SUBSCRIBER_NAME = "Name";
    protected static final String SUBSCRIBER_SURNAME = "Surname";
    protected static final String SUBSCRIBER_PASSWORD = "{bcrypt}$2a$10$/X23SC1fw0u1IHatW3kKRuHnt9sME2VGfmX6J95OBkOmwdlNnslGq";
    protected static final String SUBSCRIBER_PHONE = "+77770001144";
    protected static final String SUBSCRIBER_OTHER_PHONE = "+77770001155";
    protected static final float  SUBSCRIBER_BALANCE = 50.5F;

    @Autowired
    protected SubscriberService subscriberService;

    @Autowired
    protected TariffRepository tariffRepository;

    @Rule
    public SubscriberServiceTestRule testRule;

    @PostConstruct
    protected void init() {
        testRule = new SubscriberServiceTestRule(subscriberService, tariffRepository);
    }

    public SubscriberEntity createSubscriber() {
        boolean error = false;
        TariffEntity tariff = TariffEntity.builder()
                .name("Basic")
                .createdAt(LocalDateTime.now())
                .build();
        tariff = tariffRepository.save(tariff);

        SubscriberEntity subscriber = SubscriberEntity.builder()
                .name(SUBSCRIBER_NAME)
                .surname(SUBSCRIBER_SURNAME)
                .password(SUBSCRIBER_PASSWORD)
                .phone(SUBSCRIBER_PHONE)
                .balance(SUBSCRIBER_BALANCE)
                .createdAt(LocalDateTime.now())
                .tariff(tariff)
                .build();

        try {
            subscriber = subscriberService.save(subscriber);
            List<Subscriber> subscribers = subscriberService.getAllSubscribers();
            assertThat(subscribers, not(empty()));
        } catch (Exception ex) {
            ex.printStackTrace();
            error = true;
        }
        assertEquals(error, false);
        return subscriber;
    }

    @Test
    public void getAllSubscribersTest() {
        assertDoesNotThrow(() -> {
            createSubscriber();
            assertThat(subscriberService.getAllSubscribers(), not(empty()));
        });
    }

    @Test
    public void getSubscriberByIdTest() {
        SubscriberEntity subscriber = createSubscriber();
        Optional<Subscriber> foundSubscriber = subscriberService.getSubscriberById(subscriber.getId());
        assertThat(foundSubscriber.isPresent(), is(true));
        assertThat(subscriber.getId(), is(foundSubscriber.get().getId()));
    }

    @Test (expected = Test.None.class)
    public void saveSubscriberTest() {
        assertDoesNotThrow(() -> {
            SubscriberEntity subscriber = createSubscriber();
            List<Subscriber> subscribers = subscriberService.getAllSubscribers();
            assertThat(subscribers, not(empty()));
            assertThat(subscribers.get(0).getId(), is(subscriber.getId()));
        });
    }

    @Test
    public void loadSubscriberByPhoneTest_positive() throws Exception {
        SubscriberEntity subscriber = createSubscriber();
        SubscriberEntity foundSubscriber = subscriberService.getSubscriberEntityByPhone(SUBSCRIBER_PHONE);
        assertThat(foundSubscriber, notNullValue());
        assertThat(foundSubscriber.getPhone(), is(SUBSCRIBER_PHONE));
        assertThat(subscriber.getId(), is(foundSubscriber.getId()));
    }

    @Test(expected = ResponseStatusException.class)
    public void loadSubscriberByPhoneTest_negative() throws Exception {
        createSubscriber();
        subscriberService.getSubscriberEntityByPhone(SUBSCRIBER_OTHER_PHONE);
    }
}
