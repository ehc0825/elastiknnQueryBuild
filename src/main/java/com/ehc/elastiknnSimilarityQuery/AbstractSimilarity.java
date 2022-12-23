package com.ehc.elastiknnSimilarityQuery;

public abstract class AbstractSimilarity
{
    public String similarityName;

    /**
     * @param from int
     * @param size int
     * @param fieldName fieldNameMapping type ElastiKnn vector
     * @param vector vectorArray for ElastiKnn
     * @return elastiknn Simlarity Query FirstPart
     */
    public abstract String queryForSimilarity(int from, int size,String fieldName , String[] vector);
}
