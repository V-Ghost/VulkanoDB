package org.Tarmac.core.storage;

import java.nio.ByteBuffer;

/**
 * A fixed-size ring buffer (circular buffer) for storing bytes.
 * Supports byte-level writes and sequential reads with wrap-around.
 */
public class RingBuffer {

    /** Total Capacity of ring buffer */
    private final int capacity;

    /** Head pointer of Ring Buffer */
    private int head = 0;

    /** Read Pointer of Ring Buffer */
    private int read = -1;

    /** Underlying storage for the ring buffer */
    private ByteBuffer buffer;

    /**
     * Constructor to initialize buffer with given capacity
     *
     * @param capacity number of bytes the ring buffer can hold
     */
    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = ByteBuffer.allocate(capacity);
    }

    /**
     * Writes a single byte into the ring buffer.
     * May overwrite old data if buffer is full.
     *
     * @param record the byte to write
     */
    public void write(byte record) {

        if (read != -1) {
            read = (head + 1) % capacity;
        }

        if (head == capacity - 1 && read == -1) {
            read = 0;
        }

        buffer.put(head, record);
        head = (head + 1) % capacity;
    }

    /**
     * Reads up to {@code size} bytes from the ring buffer.
     * If fewer than {@code size} bytes are available, returns only what is readable.
     *
     * @param size number of bytes to read
     * @return an array of bytes read from the buffer
     * @throws IllegalArgumentException if {@code size} is less than 1
     */
    public byte[] read(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size is less than 1");
        }

        int available = getSize();
        int toRead = Math.min(size, available);
        byte[] result = new byte[toRead];

        if (read == -1) {
            read = 0;
        }

        for (int i = 0; i < toRead; i++) {
            byte data = buffer.get(read);
            result[i] = data;
            read = (read + 1) % capacity;
            if (isEmpty()) {
                break;
            }
        }

        return result;
    }

    /**
     * Checks whether the buffer is currently empty.
     *
     * @return {@code true} if no data is available to read; {@code false} otherwise
     */
    public boolean isEmpty() {
        return read == head;
    }

    /**
     * Returns the number of bytes currently stored and available to read.
     *
     * @return number of readable bytes in the buffer
     */
    public int getSize() {
        if (read == -1 ) {
            return head;
        }


        return capacity - (read - head);

    }

    /**
     * Destroys the buffer and resets its internal state.
     * Useful for releasing resources and clearing memory.
     */
    public void destroy() {
        buffer.clear();
        head = 0;
        read = -1;
        buffer = null;
        System.out.println("RingBuffer destroyed.");
    }
}