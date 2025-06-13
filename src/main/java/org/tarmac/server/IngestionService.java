package org.tarmac.server;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import Tarmac.*;
import org.tarmac.core.engine.StreamManager;

public class IngestionService extends IngestionServiceGrpc.IngestionServiceImplBase {
    @Override
    public void handshake(
            HandshakeRequest request,
            StreamObserver<HandshakeResponse> responseObserver
    ) {
        String streamId = request.getStreamId();


        if (!StreamManager.isRegistered(streamId)) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("Invalid stream ID: not found on this node.")
                            .asRuntimeException()
            );
            return;

        }
        System.out.println("StreamID: " +" - " + streamId);
        HandshakeResponse response = HandshakeResponse.newBuilder()
                .setAccepted(true)
                .setMessage("Stream ready: " + streamId)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<IngestRequest> streamIngest(StreamObserver<IngestResponse> responseObserver) {
        return new StreamObserver<>() {

            @Override
            public void onNext(IngestRequest request) {
                System.out.println("Received: " +" - " + request.getPayload().toStringUtf8());

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
