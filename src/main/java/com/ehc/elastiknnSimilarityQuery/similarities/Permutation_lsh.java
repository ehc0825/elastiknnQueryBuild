package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.ElastiknnSimilarQuery;
import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class Permutation_lsh extends AbstractSimilarity {

    public Permutation_lsh()
    {
        this.similarityName="permutation_lsh";
    }
    @Override
    public String queryForSimilarity(int from, int size, String fieldName, String[] vector) {
        return ElastiknnSimilarQuery.basedFrontQuery(fieldName,from,size)
                +ElastiknnSimilarQuery.getVectorForQuery(vector)
                +ElastiknnSimilarQuery.basedTailQueryForPermutation_lsh();
    }

    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder) throws IOException {
        builder.field(MODEL,similarityName);
        builder.field("similarity",DEFAULT_MODEL);
        builder.field("candidates",50);
    }
}
