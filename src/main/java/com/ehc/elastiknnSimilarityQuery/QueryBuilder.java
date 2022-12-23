package com.ehc.elastiknnSimilarityQuery;

import java.util.Map;

public class QueryBuilder {

    /**
     * @param from int
     * @param size int
     * @param fieldName fieldNameMapping type ElastiKnn vector
     * @param vector vectorArray for ElastiKnn
     * @return e해당 similarityName에 맞는 ImageSearchQuery를 return
     */
    public static String buildKnnQuery(SimilarityOption similarityOption,String fieldName,int from,int size, String[] vector)
    {
        Map<String, Similarity> similarityMap=Similarities.getSimilarityMap();
        return similarityMap.get(similarityOption.toString()).queryForSimilarity(from,size,fieldName,vector);
    }
}
