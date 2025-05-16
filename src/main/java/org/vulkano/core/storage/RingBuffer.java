package org.vulkano.core.storage;

import org.vulkano.core.model.storage.Record;

import java.nio.ByteBuffer;

public class RingBuffer {

    private final int capacity;
    private int head = 0;
    private int tail = 0;
    private  ByteBuffer buffer;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = ByteBuffer.allocateDirect(1024);
    }

    public void write(byte record) {
        buffer.put(head, record);
        head = (head + 1) % capacity;

        if (head == tail) {
            tail = (tail + 1) % capacity;
        }
    }

    public byte read() {
        if (isEmpty()) {
            throw new RuntimeException("Buffer is empty");
        }
        byte data = buffer.get(tail);
        tail = (tail + 1) % capacity;
        return data;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return head >= tail ? head - tail : capacity - tail + head;
    }

    public void destroy(){
        buffer.clear(); // Clear the internal queue
        head = 0;
        tail = 0;
        // Optionally null out buffer to help GC
        buffer = null;

        System.out.println("RingBuffer destroyed.");
    }
}