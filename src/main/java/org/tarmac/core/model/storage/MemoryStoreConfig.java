package org.tarmac.core.model.storage;

public class MemoryStoreConfig {
    public final int ringBufferSize;
    public final String streamId;
    public final int flushThreshold;
    public final boolean enableAutoFlush;

    public MemoryStoreConfig(Builder builder) {
        this.ringBufferSize = builder.ringBufferSize;
        this.streamId = builder.streamId;
        this.flushThreshold = builder.flushThreshold;
        this.enableAutoFlush = builder.enableAutoFlush;
    }

    public static class Builder {
        private int ringBufferSize = 1024;        // default
        private String streamId;                  // required
        private int flushThreshold = 0;        // default
        private boolean enableAutoFlush = true;   // default

        public Builder(String streamId) {
            this.streamId = streamId;
        }

        public Builder ringBufferSize(int ringBufferSize) {
            this.ringBufferSize = ringBufferSize;
            return this;
        }

        public Builder flushThreshold(int flushThreshold) {
            this.flushThreshold = flushThreshold;
            return this;
        }

        public Builder enableAutoFlush(boolean enableAutoFlush) {
            this.enableAutoFlush = enableAutoFlush;
            return this;
        }

        public MemoryStoreConfig build() {
            if (flushThreshold > ringBufferSize) {
                throw new IllegalArgumentException(
                        "flushThreshold (" + flushThreshold + ") cannot be greater than ringBufferSize (" + ringBufferSize + ")"
                );
            }
            return new MemoryStoreConfig(this);
        }
    }
}