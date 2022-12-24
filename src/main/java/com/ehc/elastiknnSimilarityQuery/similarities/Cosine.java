package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class Cosine extends AbstractSimilarity {

    public Cosine()
    {
        this.similarityName="cosine";
    }


    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder, Option option) throws IOException {
        builder.field(MODEL,DEFAULT_MODEL);
        builder.field(SIMILARITY,ANGULAR);
        builder.field(CANDIDATES,option.getCandidates());
    }
}
