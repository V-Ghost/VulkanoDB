package org.tarmac.core.engine;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.tarmac.core.storage.MemoryStore;
import org.tarmac.core.model.storage.MemoryStoreConfig;

public class StreamManager {
    private static final String HOME_DIR = System.getProperty("user.home");
    static {
        File tarmacDir = new File(HOME_DIR, "tarmac");
        if (!tarmacDir.exists()) {
            boolean created = tarmacDir.mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create tarmac directory at: " + tarmacDir.getAbsolutePath());
            }
        }
    }
    private static final String DB_PATH = HOME_DIR + File.separator + "tarmac" + File.separator + "streams.db";
    private static final File streamDbFile = new File(DB_PATH);
    private static final Map<String, MemoryStore> streamRegistry = new ConcurrentHashMap<>();

    // Prevent instantiation
    private StreamManager() {}

    public static synchronized void createStream(MemoryStoreConfig config) {
        if (streamRegistry.containsKey(config.streamId)) return;

        MemoryStore store = new MemoryStore(config);
        store.startIfNotRunning();
        streamRegistry.put(config.streamId, store);
        persistStreamId(config.streamId);
    }

    public static void restoreStreams() {
        if (!streamDbFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(streamDbFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String streamId = line.trim();
                MemoryStoreConfig config = new MemoryStoreConfig.Builder(streamId).build(); // default buffer size
                createStream(config);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void persistStreamId(String streamId) {
        try (FileWriter writer = new FileWriter(streamDbFile, true)) {
            writer.write(streamId + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MemoryStore get(String streamId) {
        return streamRegistry.get(streamId);
    }

    public static boolean isRegistered(String streamId) {
        return streamRegistry.containsKey(streamId);
    }

    public static Map<String, MemoryStore> listAll() {
        return streamRegistry;
    }
}