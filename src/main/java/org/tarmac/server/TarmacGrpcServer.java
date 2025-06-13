package org.tarmac.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class TarmacGrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new IngestionService())
                .build();

        System.out.println("Tarmac gRPC server started on port 9090");
        server.start();
        server.awaitTermination();
    }


}
