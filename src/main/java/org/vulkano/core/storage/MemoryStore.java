package org.vulkano.core.storage;

import org.vulkano.core.utils.MemoryStoreConfig;



public class MemoryStore implements Runnable {

    private final String streamId;
    private final RingBuffer ringBuffer;
    private final MemoryStoreConfig config;


    public MemoryStore(MemoryStoreConfig config) {
        this.config = config;
        this.streamId = config.streamId;
        this.ringBuffer = new RingBuffer(config.ringBufferSize);

    }

    public void ingest(byte[] data) {
        for (byte b : data) {
            ringBuffer.write(b);
        }

    }

    public byte[] readNext() {
        byte value = ringBuffer.read();
        return new byte[]{value}; // placeholder
    }

    @Override
    public void run() {
        // lifecycle management for the stream (optional async logic here)
    }
    public void destroy() {
        ringBuffer.destroy();
    }

}