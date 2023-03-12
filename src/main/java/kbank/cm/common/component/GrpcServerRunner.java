package kbank.cm.common.component;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import io.grpc.Server;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GrpcServerRunner implements ApplicationRunner, DisposableBean {

    private final Server grpcServer;

    public GrpcServerRunner(Server grpcServer) {
        this.grpcServer = grpcServer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        grpcServer.start();
        log.info("gRPC server started");

        grpcServer.awaitTermination();;
    }

    @Override
    public void destroy() throws Exception {
        if(grpcServer != null) {
            log.info("gRPC server will be stopped");
            grpcServer.shutdown();
        }
    }
    
}
