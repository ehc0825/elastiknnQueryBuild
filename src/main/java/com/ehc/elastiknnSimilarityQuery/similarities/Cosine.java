package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.ElastiknnSimilarQuery;
import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class Cosine extends AbstractSimilarity {

    public Cosine()
    {
        this.similarityName="cosine";
    }
    @Override
    public String queryForSimilarity(int from, int size, String fieldName, String[] vector, Option option) {
        if(option.getCandidates()==0)
        {
            option.setCandidates(50);
        }
        return baseQuery(from,size,fieldName,vector)
                +ElastiknnSimilarQuery.basedTailQueryForCosine(option.getCandidates());

    }
    private String baseQuery(int from, int size, String fieldName, String[] vector){
        return ElastiknnSimilarQuery.basedFrontQuery(fieldName,from,size)
                +ElastiknnSimilarQuery.getVectorForQuery(vector);
    }

    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder, Option option) throws IOException {
        builder.field(MODEL,DEFAULT_MODEL);
        builder.field(SIMILARITY,ANGULAR);
        builder.field(CANDIDATES,option.getCandidates());
    }
}
