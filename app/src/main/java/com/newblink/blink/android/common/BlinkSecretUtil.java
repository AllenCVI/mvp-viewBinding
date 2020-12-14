package com.newblink.blink.android.common;


import android.util.Log;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class BlinkSecretUtil {
    private static final String secretPadding ="AES/ECB/PKCS5Padding";
    private static final String secretMethod="AES";

    public static byte[] addSecret(byte[] data, String SK) {
        try {
            SecretKeySpec sks = new SecretKeySpec(SK.getBytes(StandardCharsets.US_ASCII), secretMethod);
            Cipher cr = Cipher.getInstance(secretPadding);
            cr.init(Cipher.ENCRYPT_MODE, sks);
            return cr.doFinal(data);
        } catch (Exception exception) {
            Log.e("BlinkSecretUtil", exception.getMessage());
            return null;
        }
    }

    public static byte[] solutionSecret(byte[] data, String SK) {
        try {
            SecretKeySpec sks = new SecretKeySpec(SK.getBytes(StandardCharsets.US_ASCII), secretMethod);
            Cipher cr = Cipher.getInstance(secretPadding);
            cr.init(Cipher.DECRYPT_MODE, sks);
            return cr.doFinal(data);
        } catch (Exception e) {
            Log.e("BlinkSecretUtil", e.getMessage());
            return null;
        }
    }

}
