package org.vulkano.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class VulkanoGrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new IngestionServiceImpl())
                .build();

        System.out.println("Vulkano gRPC server started on port 9090");
        server.start();
        server.awaitTermination();
    }


}
