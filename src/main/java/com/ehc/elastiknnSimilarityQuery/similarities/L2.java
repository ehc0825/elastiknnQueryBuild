package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.ElastiknnSimilarQuery;
import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class L2 extends AbstractSimilarity {
    public L2()
    {
        this.similarityName=L2;
    }
    @Override
    public String queryForSimilarity(int from, int size, String fieldName, String[] vector, Option option) {

            if(option.getCandidates()==0)
            {
                option.setCandidates(50);
            }
            if(option.getProbes()==0)
            {
                option.setProbes(2);
            }
            return baseQuery(from,size,fieldName,vector)
                    +ElastiknnSimilarQuery.basedTailQueryForL2(option.getCandidates(), option.getProbes());

    }
    private String baseQuery(int from, int size, String fieldName, String[] vector){
        return ElastiknnSimilarQuery.basedFrontQuery(fieldName,from,size)
                +ElastiknnSimilarQuery.getVectorForQuery(vector);
    }

    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder, Option option) throws IOException {
        builder.field(MODEL,DEFAULT_MODEL);
        builder.field(SIMILARITY,L2);
        builder.field(CANDIDATES,option.getCandidates());
        builder.field(PROBES,option.getProbes());
    }
}
