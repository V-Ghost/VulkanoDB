package org.vulkano;

import org.vulkano.client.VulkanoGrpcClient;
import org.vulkano.server.VulkanoGrpcServer;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: ./gradlew run --args=\"server\" or \"client\"");
            return;
        }

        switch (args[0]) {
            case "server" -> VulkanoGrpcServer.main(new String[0]);
            case "client" -> VulkanoGrpcClient.main(new String[0]);
            default -> System.out.println("Unknown mode: " + args[0]);
        }
    }
}