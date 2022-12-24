package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class L2 extends AbstractSimilarity {
    public L2()
    {
        this.similarityName=L2;
    }


    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder, Option option) throws IOException {
        builder.field(MODEL,DEFAULT_MODEL);
        builder.field(SIMILARITY,L2);
        builder.field(CANDIDATES,option.getCandidates());
        builder.field(PROBES,option.getProbes());
    }
}
