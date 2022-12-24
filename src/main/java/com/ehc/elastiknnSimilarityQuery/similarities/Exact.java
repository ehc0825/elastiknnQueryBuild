package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import com.ehc.elastiknnSimilarityQuery.ElastiknnSimilarQuery;
import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class Exact extends AbstractSimilarity {

    public Exact()
    {
        this.similarityName=EXACT;
    }

    @Override
    public String queryForSimilarity(int from, int size, String fieldName, String[] vector, Option option) {
        return ElastiknnSimilarQuery.basedFrontQuery(fieldName,from,size)
                +ElastiknnSimilarQuery.getVectorForQuery(vector)
                +ElastiknnSimilarQuery.basedTailQueryForExact();
    }

    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder, Option option) throws IOException {
        builder.field(MODEL,EXACT);
        builder.field(SIMILARITY,ANGULAR);
    }
}
