package kbank.cm.common.service;

import io.grpc.stub.StreamObserver;

import kbank.cm.common.EncryptUtilGrpc.EncryptUtilImplBase;
import kbank.cm.common.EncryptUtilOuterClass.Encrypt;
import kbank.cm.common.EncryptUtilOuterClass.Plain;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class EncryptUtilImpl extends EncryptUtilImplBase {

    @Override
    public void encrypt(Plain request, StreamObserver<Encrypt> responseObserver) {

        String encStr = Base64.encodeBase64String(request.getValue().getBytes());

        Encrypt encrypt = Encrypt.newBuilder().setValue(encStr).build();

        responseObserver.onNext(encrypt);
        responseObserver.onCompleted();
    }

    @Override
    public void decrypt(Encrypt request, StreamObserver<Plain> responseObserver) {

        String plainStr = new String(Base64.decodeBase64(request.getValue()));

        Plain plain = Plain.newBuilder().setValue(plainStr).build();

        responseObserver.onNext(plain);
        responseObserver.onCompleted();
    }
    
}
