package com.udemo.utils.security;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Desc: RSA
 * User: hansh
 * Date: 2017/12/13
 * Time: 14:40
 */
public class RSAUtil {

    private static RSAUtil instance = new RSAUtil();

    public RSAUtil() {
    }

    public static RSAUtil getInstance() {
        return instance;
    }

    private void generateKeyPair(String key_path, String name_prefix) {
        KeyPairGenerator keygen = null;

        try {
            keygen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException var13) {
            var13.printStackTrace();
        }

        SecureRandom secrand = new SecureRandom();
        secrand.setSeed("21cn".getBytes());

        assert keygen != null;

        keygen.initialize(1024, secrand);
        KeyPair keys = keygen.genKeyPair();
        PublicKey pubkey = keys.getPublic();
        PrivateKey prikey = keys.getPrivate();
        String pubKeyStr = Base64Util.getBASE64(pubkey.getEncoded());
        String priKeyStr = Base64Util.getBASE64(prikey.getEncoded());
        File file = new File(key_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            FileOutputStream fos = new FileOutputStream(new File(key_path + name_prefix + "_RSAKey_private.txt"));

            assert priKeyStr != null;

            fos.write(priKeyStr.getBytes());
            fos.close();
            fos = new FileOutputStream(new File(key_path + name_prefix + "_RSAKey_public.txt"));
            fos.write(pubKeyStr.getBytes());
            fos.close();
        } catch (IOException var12) {
            var12.printStackTrace();
        }

    }

    private static String getKeyContent(String key_file) {
        File file = new File(key_file);
        BufferedReader br = null;
        FileInputStream ins = null;
        StringBuilder sReturnBuf = new StringBuilder();

        try {
            ins = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));

            for(String e = br.readLine(); e != null; e = br.readLine()) {
                sReturnBuf.append(e);
            }

            String var6 = sReturnBuf.toString();
            return var6;
        } catch (IOException var20) {
            var20.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }
            }

            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

        }

        return null;
    }

    public static String sign(String prikeyvalue, String sign_str) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.getBytesBASE64(prikeyvalue));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
            Signature signet = Signature.getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(sign_str.getBytes("UTF-8"));
            byte[] signed = signet.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (Exception var7) {
            return null;
        }
    }

    public static boolean checksign(String pubkeyvalue, String oid_str, String signed_str) {
        try {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64Util.getBytesBASE64(pubkeyvalue));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64Util.getBytesBASE64(signed_str);
            Signature signetcheck = Signature.getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(oid_str.getBytes("UTF-8"));
            return signetcheck.verify(signed);
        } catch (Exception var8) {
            return false;
        }
    }
}
