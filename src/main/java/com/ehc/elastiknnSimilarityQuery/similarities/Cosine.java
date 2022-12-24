package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.ElastiknnSimilarQuery;
import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class Cosine extends AbstractSimilarity {

    public Cosine()
    {
        this.similarityName="cosine";
    }
    @Override
    public String queryForSimilarity(int from, int size, String fieldName, String[] vector) {
        return ElastiknnSimilarQuery.basedFrontQuery(fieldName,from,size)
                +ElastiknnSimilarQuery.getVectorForQuery(vector)
                +ElastiknnSimilarQuery.basedTailQueryForCosine();
    }

    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder) throws IOException {
        builder.field("similarity","angular");
        builder.field("candidates",50);
    }
}
