package org.vulkano.core.engine;
/*
 *handles when to transfer to disk
 *
 */


import org.vulkano.core.model.engine.QueryResult;


public class StreamEngine implements EngineOps {
    @Override
    public QueryResult execute(String queryText) {
        return null;
    }

    @Override
    public void shutdown() {
    System.out.println("Shutting Down");
    }
}