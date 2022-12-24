package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class Permutation_lsh extends AbstractSimilarity {

    public Permutation_lsh()
    {
        this.similarityName=PERMUTATION_LSH;
    }

    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder, Option option) throws IOException {
        builder.field(MODEL,PERMUTATION_LSH);
        builder.field(SIMILARITY,ANGULAR);
        builder.field(CANDIDATES,option.getCandidates());
    }
}
