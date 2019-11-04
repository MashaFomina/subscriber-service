package com.subscriber.services;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.models.Balance;
import com.subscriber.models.Subscriber;

import java.util.List;
import java.util.Optional;

public interface SubscriberService {
    /**
     * Gets all subscribers
     *
     * @return list of found subscribers
     */
    List<Subscriber> getAllSubscribers() throws Exception;

    /**
     * Gets subscriber by id
     *
     * @param  id subscriber id
     * @return full subscriber info
     */
    Optional<Subscriber> getSubscriberById(long id);

    /**
     * Gets subscriber by phone number
     *
     * @param  phone subscriber's phone number
     * @return full subscriber info
     */
    SubscriberEntity getSubscriberEntityByPhone(String phone) throws Exception;

    /**
     * Gets subscriber by phone number
     *
     * @param  phone subscriber's phone number
     * @return full subscriber info or null if subscriber not found
     */
    SubscriberEntity getSubscriberEntityByPhoneQuiet(String phone) throws Exception ;

    /**
     * Save subscriber
     *
     * @param  subscriber subscriber's info
     * @return full subscriber info
     */
    SubscriberEntity save(SubscriberEntity subscriber) throws Exception;

    /**
     * Delete all subscribers
     */
    void deleteAll();

    /**
     * Change balance of subscriber
     *
     * @param  subscriber subscriber's info
     * @param  money      money amount
     * @param  isAdding   if true we add money to balance otherwise substruct
     *
     * @return full subscriber info
     */
    SubscriberEntity changeBalance(SubscriberEntity subscriber, float money, boolean isAdding) throws Exception;

    /**
     * Gets subscriber balance by phone
     *
     * @param  phone subscriber's phone number
     * @return subscriber's balance info
     */
    Optional<Balance> getBalanceByPhone(String phone);

    /**
     * Gets subscriber balance by id
     *
     * @param  id subscriber's id
     * @return subscriber's balance info
     */
    Optional<Balance> getBalanceById(long id);

    /**
     * Add money to balance of subscriber
     *
     * @param  phone subscriber's phone number
     * @param  money money amount to add
     * @return subscriber's balance info
     */
    Balance addMoney(String phone, float money) throws Exception;
}
