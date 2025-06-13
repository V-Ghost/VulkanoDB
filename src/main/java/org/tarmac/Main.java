package org.tarmac;

import org.tarmac.client.TarmacGrpcClient;
import org.tarmac.core.engine.StreamManager;
import org.tarmac.core.model.storage.MemoryStoreConfig;
import org.tarmac.server.TarmacGrpcServer;

import java.io.IOException;

import static org.tarmac.core.engine.StreamManager.restoreStreams;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args.length > 1 && args[0].equals("--create-stream")) {
            String streamId = args[1];
            StreamManager.createStream(new MemoryStoreConfig.Builder(streamId).build());
            System.out.println("Stream created: " + streamId);
            return;
        }

        // Normal server startup
        StreamManager.restoreStreams(); // <- auto-run previously created streams
        try {
            TarmacGrpcServer.main(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Start gRPC server here
        System.out.println("Tarmac Server running...");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown triggered. Cleaning up...");
            // StreamManager.shutdownAll(); // e.g., call destroy() on all memory stores
        }));
    }
}