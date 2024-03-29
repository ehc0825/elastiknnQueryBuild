package com.ehc.elastiknnSimilarityQuery;

import com.ehc.elastiknnSimilarityQuery.similarities.Cosine;
import com.ehc.elastiknnSimilarityQuery.similarities.Exact;
import com.ehc.elastiknnSimilarityQuery.similarities.L2;
import com.ehc.elastiknnSimilarityQuery.similarities.Permutation_lsh;

import java.util.Arrays;


public enum SimilarityType {

    COSINE(new Cosine()){
        @Override
        public String toString() {
            return "cosine";
        }
    },
    L2(new L2()){
        @Override
        public String toString() {
            return "l2";
        }
    },
    PERMUTATION_LSH(new Permutation_lsh()){
        @Override
        public String toString() {
            return "permutation_lsh";
        }
    },
    EXACT(new Exact()){
        @Override
        public String toString() {
            return "exact";
        }
    };

    private final AbstractSimilarity abstractSimilarity;
    SimilarityType(AbstractSimilarity abstractSimilarity)
    {
        this.abstractSimilarity = abstractSimilarity;
    }

    public AbstractSimilarity getAbstractSimilarity()
    {
       return abstractSimilarity;
    }

    public static SimilarityType find(String similarityName) {
        return Arrays.stream(values())
                .filter(similarityType -> similarityType.toString().equals(similarityName))
                .findAny()
                .orElse(EXACT);
    }

}
