package kbank.cm.common.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EncryptService {

    @Cacheable(value = "grpc:encrypt", keyGenerator = "hashKeyGenerator", unless="#result.startsWith('ERROR')")
    public String encrypt(String plain) {
        log.info("encrypt called");
        return Base64.encodeBase64String(plain.getBytes());
        // return "ERROR OCCURS";
    }

    @Cacheable(value = "grpc:decrypt", keyGenerator = "hashKeyGenerator", unless="#result.startsWith('ERROR')")
    public String decrypt(String encrypt) {
        log.info("decrypt called");
        return new String(Base64.decodeBase64(encrypt));
        // return "ERROR OCCURS";
    }
    
}
