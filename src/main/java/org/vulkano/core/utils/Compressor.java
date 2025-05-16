package org.vulkano.core.utils;

import java.io.IOException;

import org.xerial.snappy.Snappy;

public class Compressor {
    public static byte[] compress(byte[] data) throws IOException {
        return Snappy.compress(data);
    }

    public static byte[] decompress(byte[] compressedData) throws IOException {
        return Snappy.uncompress(compressedData);
    }
}
