package com.easyicon.learnglide.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.util
 * ClassName:      MD5Util
 * Description:
 * Author:         61444
 * CreateDate:     2020/3/7 20:52
 */
public class MD5Util {
    /**
     * Encodes a string to MD5
     *
     * @param str String to encode
     * @return Encoded String
     * @throws NoSuchAlgorithmException
     */
    public static String toMD5(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (byte b : hash) {
                if ((0xff & b) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & b)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & b));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

}
