package com.zabador.motherspills.sms;

/**
 * Sms Manager Factory, provides implementations for the SMS Manager.
 * Created on 11/21/15.
 *
 * @author Adrian Pena
 */
public class SmsManagerFactory {

    /**
     * Retrieves an implementation for the SMS Manager.
     * @return an {@link SmsManager}.
     */
    public static SmsManager getSmsManager() {
        return DefaultSmsManager.getInstance();
    }
}
