package com.subscriber.services;

import com.subscriber.models.Sms;

public interface SmsService {
    /**
     * Send sms to receiver phone number
     *
     * @param  senderPhone   sender's phone number
     * @param  receiverPhone receiver's phone number
     * @param  text          sms message
     * @return sms information
     */
    Sms sendSms(String senderPhone, String receiverPhone, String text) throws Exception;
}
