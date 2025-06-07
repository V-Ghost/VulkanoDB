package org.Tarmac.core.engine;

import org.Tarmac.core.model.engine.QueryResult;

public interface QueryEngine {
    QueryResult execute(String queryText);
    void shutdown();
}