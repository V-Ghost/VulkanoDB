package org.vulkano.core.engine;

import org.vulkano.core.model.engine.QueryResult;

public interface EngineOps {
    QueryResult execute(String queryText);
    void shutdown();
}