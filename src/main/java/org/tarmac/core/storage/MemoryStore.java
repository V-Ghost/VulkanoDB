package org.tarmac.core.storage;

import org.tarmac.core.model.storage.StorageStatus;
import org.tarmac.core.model.storage.MemoryStoreConfig;



public class MemoryStore implements Runnable {

    private final String streamId;
    private final RingBuffer ringBuffer;
    private Thread thread;

    public MemoryStore(MemoryStoreConfig config) {
        this.streamId = config.streamId;
        this.ringBuffer = new RingBuffer(config.ringBufferSize);

    }

    public synchronized void startIfNotRunning() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this, "MemoryStore-" + streamId);
            System.out.println("MemoryStore-" + streamId + " is running");
            thread.start();
        }
    }

    public synchronized boolean isRunning() {
        return thread != null && thread.isAlive();
    }

    public StorageStatus ingest(byte[] data) {
        try {
            for (byte b : data) {
                ringBuffer.write(b); // optionally check for overflow
            }
            return StorageStatus.SUCCESS;
        } catch (Exception e) {
            return StorageStatus.ERROR;
        }
    }


    public byte[] readNext() {
        return ringBuffer.read(1);
    }

    public byte[] read(int size){
        return ringBuffer.read(size);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                byte[] data = readNext(); // or peek()
                if (data != null && data.length > 0) {
                    // Process, infer, log, etc.

                } else {
                    Thread.sleep(10);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            destroy();
        }
    }
    public void destroy() {
        ringBuffer.destroy();
    }

}