package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.ElastiknnSimilarQuery;
import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class L2 extends AbstractSimilarity {
    public L2()
    {
        this.similarityName=L2;
    }
    @Override
    public String queryForSimilarity(int from, int size, String fieldName, String[] vector) {
        return ElastiknnSimilarQuery.basedFrontQuery(fieldName,from,size)
                +ElastiknnSimilarQuery.getVectorForQuery(vector)
                +ElastiknnSimilarQuery.basedTailQueryForL2();
    }

    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder) throws IOException {
        builder.field(MODEL,DEFAULT_MODEL);
        builder.field(SIMILARITY,L2);
        builder.field(CANDIDATES,50);
        builder.field(PROBES,2);
    }
}
