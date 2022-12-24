package com.ehc.elastiknnSimilarityQuery;

import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public abstract class AbstractSimilarity
{
    public String similarityName;
    public static final String MODEL = "model";
    public static final String DEFAULT_MODEL = "lsh";
    public static final String SIMILARITY = "similarity";
    public static final String ANGULAR="angular";
    public static final String L2="l2";
    public static final String PERMUTATION_LSH="permutation_lsh";
    public static final String CANDIDATES="candidates";
    public static final String PROBES="probes";


    /**
     * @param from int
     * @param size int
     * @param fieldName fieldNameMapping type ElastiKnn vector
     * @param vector vectorArray for ElastiKnn
     * @return elastiknn Simlarity Query FirstPart
     */
    public abstract String queryForSimilarity(int from, int size,String fieldName , String[] vector);

    public abstract void buildKnnQueryBySimilarity(XContentBuilder builder) throws IOException;
}
