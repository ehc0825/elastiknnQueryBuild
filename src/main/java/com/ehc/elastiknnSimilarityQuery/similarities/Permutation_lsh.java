package com.ehc.elastiknnSimilarityQuery.similarities;

import com.ehc.elastiknnSimilarityQuery.ElastiknnSimilarQuery;
import com.ehc.elastiknnSimilarityQuery.AbstractSimilarity;
import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class Permutation_lsh extends AbstractSimilarity {

    public Permutation_lsh()
    {
        this.similarityName=PERMUTATION_LSH;
    }
    @Override
    public String queryForSimilarity(int from, int size, String fieldName, String[] vector, Option option) {
        if(option.getCandidates()==0)
        {
            option.setCandidates(50);
        }
        else
        {
            option.setCandidates(option.getCandidates());
        }
        return basQuery(from,size,fieldName,vector)
                +ElastiknnSimilarQuery.basedTailQueryForPermutation_lsh(option.getCandidates());
    }
    private String basQuery(int from, int size, String fieldName, String[] vector)
    {
        return ElastiknnSimilarQuery.basedFrontQuery(fieldName,from,size)
                +ElastiknnSimilarQuery.getVectorForQuery(vector);
    }

    @Override
    public void buildKnnQueryBySimilarity(XContentBuilder builder, Option option) throws IOException {
        builder.field(MODEL,PERMUTATION_LSH);
        builder.field(SIMILARITY,ANGULAR);
        builder.field(CANDIDATES,option.getCandidates());
    }
}
