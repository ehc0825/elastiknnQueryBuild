package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.ElastiknnSimilarQuery;
import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;

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
}
