package org.vulkano.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import vulkano.IngestionServiceGrpc;
import vulkano.IngestRequest;
import vulkano.IngestResponse;

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

    static class IngestionServiceImpl extends IngestionServiceGrpc.IngestionServiceImplBase {
        @Override
        public StreamObserver<IngestRequest> streamIngest(StreamObserver<IngestResponse> responseObserver) {
            return new StreamObserver<>() {
                @Override
                public void onNext(IngestRequest request) {
                    System.out.println("Received: " + request.getStreamId() + " - " + request.getPayload().toStringUtf8());
                    // You could write to MemoryStore here
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Stream error: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    responseObserver.onNext(IngestResponse.newBuilder()
                            .setStatus("Ingest complete")
                            .build());
                    responseObserver.onCompleted();
                }
            };
        }
    }
}
