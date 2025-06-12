package org.tarmac;

import org.tarmac.client.TarmacGrpcClient;
import org.tarmac.server.TarmacGrpcServer;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: ./gradlew run --args=\"server\" or \"client\"");
            return;
        }

        switch (args[0]) {
            case "server" -> TarmacGrpcServer.main(new String[0]);
            case "client" -> TarmacGrpcClient.main(new String[0]);
            default -> System.out.println("Unknown mode: " + args[0]);
        }
    }
}