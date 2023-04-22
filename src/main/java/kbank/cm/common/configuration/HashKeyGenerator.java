package kbank.cm.common.configuration;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashKeyGenerator implements KeyGenerator {
   
    @Override
    public Object generate(Object target, Method method, Object... params) {
        String key = target.getClass().getSimpleName() + "_"
                + method.getName() + "_"
                + StringUtils.arrayToDelimitedString(params, "_");

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(key.getBytes());

            String hexKey = Hex.encodeHexString(md.digest());

            log.info("key: " + hexKey);
            
            return hexKey;
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            return key;
        }
    }
}
