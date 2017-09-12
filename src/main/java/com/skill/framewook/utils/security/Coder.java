package com.skill.framewook.utils.security;

import java.security.MessageDigest;

import org.springframework.util.Base64Utils;

/**
 * 基础加密组件
 *
 * @version 1.0
 * @since 1.0
 */
public abstract class Coder {
    public static final String KEY_SHA = "SHA";

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64ToStr(String key) {
      //  return new String((new BASE64Decoder()).decodeBuffer(key), "UTF-8");
    //	return new String(Base64.decodeBase64(key));
    	return new String(Base64Utils.decodeFromString(key));
    }

    public static byte[] decryptBASE64(String key) {
      //  return (new BASE64Decoder()).decodeBuffer(key);
    	//return Base64.decodeBase64(key);
    	return  Base64Utils.decodeFromString(key);
    }

    /**
     * BASE64加密
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] bytes) {
    	return Base64Utils.encodeToString(bytes);
    //	return Base64.encodeBase64String(bytes);
    	// return (new BASE64Encoder()).encode(bytes);
    }

    public static String encryptStrToBASE64(String str) {
    	
    	return Base64Utils.encodeToString(str.getBytes());
    //	return Base64.encodeBase64String(str.getBytes());
    	//  return (new BASE64Encoder()).encode(str.getBytes("UTF-8"));
    }

    public static String encryptMD5(String str) {
        return Md5Util.hash(str);
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }

    /**
     * Turns array of bytes into string
     *
     * @param buf Array of bytes to convert to hex string
     * @return Generated hex string
     */
    public static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }
    
    public static void main(String args[]) throws Exception{
    	String encryptStrToBASE64 = Coder.encryptStrToBASE64("root");
    	System.out.println(encryptStrToBASE64);
    	String decryptBASE64ToStr = Coder.decryptBASE64ToStr(encryptStrToBASE64);
    	System.out.println(decryptBASE64ToStr);
    }
}