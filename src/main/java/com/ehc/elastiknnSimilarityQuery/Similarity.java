package com.ehc.elastiknnSimilarityQuery;

import com.ehc.elastiknnSimilarityQuery.similarities.Cosine;
import com.ehc.elastiknnSimilarityQuery.similarities.Exact;
import com.ehc.elastiknnSimilarityQuery.similarities.L2;
import com.ehc.elastiknnSimilarityQuery.similarities.Permutation_lsh;

import java.util.HashMap;
import java.util.Map;

public enum Similarity {

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

    private AbstractSimilarity abstractSimilarity;
    Similarity(AbstractSimilarity abstractSimilarity)
    {
        this.abstractSimilarity = abstractSimilarity;
    }

    /**
     * Similarity이름과 Similarity 세트를 Map형태로 return
     */
    public static Map<String, AbstractSimilarity> getSimilarityMap(){
        Map<String, AbstractSimilarity> similarityMap= new HashMap<>();
        for(Similarity similarity: Similarity.values())
        {
            similarityMap.put(similarity.abstractSimilarity.similarityName,similarity.abstractSimilarity);
        }
        return similarityMap;
    }

}
