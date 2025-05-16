package org.vulkano.core.model.storage;

import java.time.LocalDateTime;

public record Record(
        String id,
        byte[] payload,              // Serialized data (e.g. Avro, JSON, custom binary)
        LocalDateTime storedAt,
        LocalDateTime generatedAt,// When the data was created or ingested
        String source,               // Optional: where this data came from (e.g. "stream-1", "disk", "API")
        long sequence                // Optional: monotonically increasing ID for ordering
) {
}
