package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class Exact extends AbstractSimilarity {

    public Exact()
    {
        this.similarityName=EXACT;
    }


    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder, Option option) throws IOException {
        builder.field(MODEL,EXACT);
        builder.field(SIMILARITY,ANGULAR);
    }
}
