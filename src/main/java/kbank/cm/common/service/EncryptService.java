package kbank.cm.common.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@CacheConfig(cacheNames={"grpc"})
public class EncryptService {

    @Cacheable(value = "grpc:encrypt", keyGenerator = "hashKeyGenerator")
    public String encrypt(String plain) {
        log.info("encrypt called");
        return Base64.encodeBase64String(plain.getBytes());
    }

    @Cacheable(value = "grpc:decrypt", keyGenerator = "hashKeyGenerator")
    public String decrypt(String encrypt) {
        log.info("decrypt called");
        return new String(Base64.decodeBase64(encrypt));
    }
    
}
