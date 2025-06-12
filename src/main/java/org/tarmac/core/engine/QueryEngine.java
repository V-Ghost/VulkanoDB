package org.tarmac.core.engine;

import org.tarmac.core.model.engine.QueryResult;

public interface QueryEngine {
    QueryResult execute(String queryText);
    void shutdown();
}