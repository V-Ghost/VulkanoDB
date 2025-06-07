package org.Tarmac.client;


import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import Tarmac.*;

public class TarmacGrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        IngestionServiceGrpc.IngestionServiceStub stub = IngestionServiceGrpc.newStub(channel);

        IngestionServiceGrpc.IngestionServiceBlockingStub blockingStub =
                IngestionServiceGrpc.newBlockingStub(channel);

        HandshakeRequest request = HandshakeRequest.newBuilder()
                .setClientId("client-001")
                .setStreamId("stream-sensor-a")
                .setUseCompression(false)
                .build();


        HandshakeResponse response = blockingStub.handshake(request);

        if (response.getAccepted()) {
            System.out.println("🟢 Handshake succeeded: " + response.getMessage());
            StreamObserver<IngestRequest> requestObserver = stub.streamIngest(new StreamObserver<>() {
                @Override
                public void onNext(IngestResponse ingestResponse) {
                    System.out.println("Server response: " + ingestResponse.getStatus());
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error from server: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Stream closed by server");
                }
            });

            for (int i = 1; i <= 5; i++) {
                IngestRequest req = IngestRequest.newBuilder()
                        .setPayload(ByteString.copyFrom(("payload-" + i).getBytes()))
                        .setTimestamp(System.currentTimeMillis())
                        .build();
                requestObserver.onNext(req);

            }

            requestObserver.onCompleted();
            try {
                Thread.sleep(1000); // wait for server ack
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("🔴 Handshake rejected: " + response.getMessage());
        }



        channel.shutdownNow();
    }
}