package org.Tarmac.core.utils;

public class MemoryStoreConfig {
    public final int ringBufferSize;
    public final String streamId;
    public final int flushThreshold;  // records or bytes
    public final boolean enableAutoFlush;

    public MemoryStoreConfig(String streamId, int ringBufferSize, int flushThreshold, boolean enableAutoFlush) {
        this.streamId = streamId;
        this.ringBufferSize = ringBufferSize;
        this.flushThreshold = flushThreshold;
        this.enableAutoFlush = enableAutoFlush;
    }
}