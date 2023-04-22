package kbank.cm.common.configuration;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import kbank.cm.common.service.EncryptUtilImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfiguration {

    @Value("${grpc.server.port}")
    private int grpcServerPort;

    @Bean
    public Server grpcServer(EncryptUtilImpl encryptUtil) {
        return ServerBuilder.forPort(grpcServerPort)
            .addService(encryptUtil)
            .build();
    }
    
}
