package com.ehc.elastiknnSimilarityQuery;

import java.util.Map;

public class QueryBuilder {

    /**
     * @param from int
     * @param size int
     * @param fieldName fieldNameMapping type ElastiKnn vector
     * @param vector vectorArray for ElastiKnn
     * @return 해당 similarityName에 맞는 ImageSearchQuery를 return
     */
    public static String buildKnnQuery(Similarity similarity, String fieldName, int from, int size, String[] vector)
    {
        Map<String, AbstractSimilarity> similarityMap= Similarity.getSimilarityMap();
        return similarityMap.get(similarity.toString()).queryForSimilarity(from,size,fieldName,vector);
    }
}
