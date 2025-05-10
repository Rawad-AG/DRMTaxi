package dev.taxiapp.taxi_app.Utils;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonenumber;


public class PhoneNumberChecker {
    public static boolean isValidPhone(String phoneNumber, String region) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, region);

            return phoneUtil.isValidNumber(numberProto);
        } catch (NumberParseException e) {
            return false;
        }
    }
}



