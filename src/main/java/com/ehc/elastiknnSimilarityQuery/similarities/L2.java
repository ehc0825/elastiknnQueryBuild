package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.ElastiknnSimilarQuery;
import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class L2 extends AbstractSimilarity {
    public L2()
    {
        this.similarityName="l2";
    }
    @Override
    public String queryForSimilarity(int from, int size, String fieldName, String[] vector) {
        return ElastiknnSimilarQuery.basedFrontQuery(fieldName,from,size)
                +ElastiknnSimilarQuery.getVectorForQuery(vector)
                +ElastiknnSimilarQuery.basedTailQueryForL2();
    }

    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder) throws IOException {
        builder.field("similarity",similarityName);
        builder.field("candidates",50);
        builder.field("probes",2);
    }
}
