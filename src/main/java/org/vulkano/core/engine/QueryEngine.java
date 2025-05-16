package org.vulkano.core.engine;

import org.vulkano.core.model.engine.QueryResult;

public interface QueryEngine {
    QueryResult execute(String queryText);
    void shutdown();
}