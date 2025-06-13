package org.tarmac.core.storage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DiskBuffer implements ByteBuffer {
    private final RandomAccessFile file;
    private final File backingFile;
    private long writePointer = 0;
    private long readPointer = 0;

    public DiskBuffer(String filePath) {
        try {
            this.backingFile = new File(filePath);
            this.file = new RandomAccessFile(backingFile, "rw");
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize DiskBuffer", e);
        }
    }

    @Override
    public synchronized void write(byte record) {
        try {
            file.seek(writePointer);
            file.writeByte(record);
            writePointer++;
        } catch (IOException e) {
            throw new RuntimeException("Disk write failed", e);
        }
    }

    @Override
    public synchronized byte[] read(int size) {
        try {
            long available = writePointer - readPointer;
            int toRead = (int) Math.min(size, available);

            if (toRead <= 0) return new byte[0];

            byte[] buffer = new byte[toRead];
            file.seek(readPointer);
            file.readFully(buffer);
            readPointer += toRead;
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException("Disk read failed", e);
        }
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public int getSize() {
        return (int) (writePointer - readPointer);
    }

    @Override
    public void destroy() {
        try {
            file.close();
            backingFile.delete(); // optional: wipe file
        } catch (IOException e) {
            throw new RuntimeException("Failed to destroy DiskBuffer", e);
        }
    }
}