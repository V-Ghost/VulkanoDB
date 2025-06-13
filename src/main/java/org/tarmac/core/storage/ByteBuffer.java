package org.tarmac.core.storage;

public interface ByteBuffer {
    /**
            * Write a single byte into the ring buffer.
     */
    void write(byte record);

    /**
     * Read up to {@code size} bytes from the buffer.
     */
    byte[] read(int size);

    /**
     * Check if the buffer is currently empty.
     */
    boolean isEmpty();

    /**
     * Get the number of bytes available to read.
     */
    int getSize();

    /**
     * Clear and destroy the buffer.
     */
    void destroy();
}
