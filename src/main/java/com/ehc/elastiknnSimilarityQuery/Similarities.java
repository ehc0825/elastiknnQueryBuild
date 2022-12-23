package com.ehc.elastiknnSimilarityQuery;

import com.ehc.elastiknnSimilarityQuery.similarities.Cosine;
import com.ehc.elastiknnSimilarityQuery.similarities.L2;
import com.ehc.elastiknnSimilarityQuery.similarities.Permutation_lsh;

import java.util.HashMap;
import java.util.Map;

public enum Similarities {

    COSINE(new Cosine()),
    L2(new L2()),
    PERMUTATION_LSH(new Permutation_lsh());

    private Similarity similarity;
    Similarities(Similarity similarity)
    {
        this.similarity=similarity;
    }


    /**
     * Similarity이름과 Similarity 세트를 Map형태로 return
     */
    public static Map<String, Similarity> getSimilarityMap(){
        Map<String, Similarity> similarityMap= new HashMap<>();
        for(Similarities similarity: Similarities.values())
        {
            similarityMap.put(similarity.similarity.similarityName,similarity.similarity);
        }
        return similarityMap;
    }

}
