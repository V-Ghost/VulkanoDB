package org.vulkano.core.storage;

import org.vulkano.core.model.storage.Record;

import java.nio.ByteBuffer;

public class RingBuffer {

    private final int capacity;
    private int head = 0;
    private int tail = 0;
    private final ByteBuffer buffer;

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
}