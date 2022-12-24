package com.ehc.elastiknnSimilarityQuery;

import com.ehc.elastiknnSimilarityQuery.similarities.Cosine;
import org.apache.lucene.search.Query;
import org.elasticsearch.Version;
import org.elasticsearch.common.ParseField;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.search.MatchQuery;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class KnnQueryBuilder extends AbstractQueryBuilder<KnnQueryBuilder>{


    public static final String NAME = "elastiknn_nearest_neighbors";
    public static final String FIELD = "field";
    public static final String VALUE_FIELD_NAME = "values";
    public static final ParseField VEC = new ParseField("vec");
    private final String fieldName;
    private final Similarity similarity;
    private final String[] value;

    /**
     * fiendName: elastiknn 으로 맵핑된 필드명
     * similarity: 사용할 유사도(cosine,l2, permutation_lsh,exact)
     */
    public KnnQueryBuilder(String fieldName,Similarity similarity, String[] value) {
        if (fieldName == null) {
            throw new IllegalArgumentException("[" + NAME + "] requires fieldName");
        }
        if (similarity == null) {
            throw new IllegalArgumentException("[" + NAME + "] requires similarity");
        }
        if (value == null) {
            throw new IllegalArgumentException("[" + NAME + "] requires query value");
        }
        this.similarity=similarity;
        this.fieldName = fieldName;
        this.value = value;
    }
    public KnnQueryBuilder(StreamInput in) throws IOException{
        super(in);
        fieldName=in.readString();
        similarity= in.readEnum(Similarity.class);
        value=in.readOptionalStringArray();

    }
    @Override
    protected void doWriteTo(StreamOutput out) throws IOException {
        out.writeString(fieldName);
        out.writeEnum(similarity);
        out.writeGenericValue(value);
    }
    public String fieldName() {
        return this.fieldName;
    }
    public Similarity similarity() {
        return this.similarity;
    }
    public String[] value() {
        return this.value;
    }

    @Override
    protected void doXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(NAME);
        builder.field(FIELD,fieldName);
        builder.startObject(VEC.getPreferredName());
        builder.field(VALUE_FIELD_NAME, value);
        builder.endObject();
        buildBySimilarity(builder,similarity);
        builder.endObject();
    }
    protected void buildBySimilarity(XContentBuilder builder, Similarity similarity) throws IOException {
        Map<String, AbstractSimilarity> similarityMap= Similarity.getSimilarityMap();
        similarityMap.get(similarity.toString()).buildKnnQueryBySimilarity(builder);
    }

    @Override
    protected Query doToQuery(QueryShardContext context) throws IOException {
        return null;
    }

    @Override
    protected boolean doEquals(KnnQueryBuilder other) {
        return  Objects.equals(fieldName, other.fieldName) &&
                Objects.equals(value, other.value);
    }

    @Override
    protected int doHashCode() {
        return Objects.hash(fieldName, value);
    }

    @Override
    public String getWriteableName() {
        return NAME;
    }


    /**
     * @param from int
     * @param size int
     * @param fieldName fieldNameMapping type ElastiKnn vector
     * @param vector vectorArray for ElastiKnn
     * @return 해당 similarityName에 맞는 ImageSearchQuery를 return
     */
    public static String buildStringKnnQuery(Similarity similarity, String fieldName, int from, int size, String[] vector)
    {
        Map<String, AbstractSimilarity> similarityMap= Similarity.getSimilarityMap();
        return similarityMap.get(similarity.toString()).queryForSimilarity(from,size,fieldName,vector);
    }
}
