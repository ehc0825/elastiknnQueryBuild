package com.ehc.elastiknnSimilarityQuery;


import com.ehc.elastiknnSimilarityQuery.query.KnnQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;

class KnnQueryBuilderTest {

    @Test
    public void queryBuildTest()
    {
        String[] vector={"0.9217332601547241", "1.523964524269104", "1.6131728887557983", "1.0742337703704834", "0.26549723744392395", "0.2835127115249634", "0.04264784976840019"};
        KnnQueryBuilder knnQueryBuilder=new KnnQueryBuilder("vector",Similarity.L2,vector,15,3);
        SearchSourceBuilder searchSourceBuilder= new SearchSourceBuilder();
        searchSourceBuilder.query(knnQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        System.out.println(searchSourceBuilder);
    }
    @Test
    public void findTest()
    {
        String[] vector={"0.9217332601547241", "1.523964524269104", "1.6131728887557983", "1.0742337703704834", "0.26549723744392395", "0.2835127115249634", "0.04264784976840019"};
        KnnQueryBuilder knnQueryBuilder=new KnnQueryBuilder("vector",Similarity.find("cosine"),vector);
        SearchSourceBuilder searchSourceBuilder= new SearchSourceBuilder();
        searchSourceBuilder.query(knnQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        System.out.println(searchSourceBuilder);
    }
}