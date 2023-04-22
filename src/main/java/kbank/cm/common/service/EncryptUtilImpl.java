package kbank.cm.common.service;

import io.grpc.stub.StreamObserver;

import kbank.cm.common.EncryptUtilGrpc.EncryptUtilImplBase;
import kbank.cm.common.EncryptUtilOuterClass.Encrypt;
import kbank.cm.common.EncryptUtilOuterClass.Plain;

import org.springframework.stereotype.Service;

@Service
public class EncryptUtilImpl extends EncryptUtilImplBase {

    private final EncryptService encryptService;

    EncryptUtilImpl(EncryptService encryptService) {
        this.encryptService = encryptService;
    }

    @Override
    public void encrypt(Plain request, StreamObserver<Encrypt> responseObserver) {

        String encStr = encryptService.encrypt(request.getValue());

        Encrypt encrypt = Encrypt.newBuilder().setValue(encStr).build();

        responseObserver.onNext(encrypt);
        responseObserver.onCompleted();
    }

    @Override
    public void decrypt(Encrypt request, StreamObserver<Plain> responseObserver) {

        String plainStr = encryptService.decrypt(request.getValue());

        Plain plain = Plain.newBuilder().setValue(plainStr).build();

        responseObserver.onNext(plain);
        responseObserver.onCompleted();
    }
    
}
