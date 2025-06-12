package org.tarmac.core.engine;
/*
 *handles when to transfer to disk
 *
 */


import org.tarmac.core.model.engine.QueryResult;


public class StreamQueryEngine implements QueryEngine {
    @Override
    public QueryResult execute(String queryText) {
        return null;
    }

    @Override
    public void shutdown() {
    System.out.println("Shutting Down");
    }
}