package org.vulkano;

import org.vulkano.core.engine.StreamEngine;

public class Main {
    public static void main(String[] args) {
        StreamEngine streamEngine = new StreamEngine();
        streamEngine.shutdown();
    }
}