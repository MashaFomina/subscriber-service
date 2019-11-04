package com.subscriber.services;

import com.subscriber.models.Call;

public interface CallService {
    /**
     * Make call to phone number
     *
     * @param  callerPhone   caller's phone number
     * @param  receiverPhone receiver's phone number
     * @param  duration      duration of call in minutes
     * @return sms information
     */
    Call makeCall(String callerPhone, String receiverPhone, float duration) throws Exception;
}
