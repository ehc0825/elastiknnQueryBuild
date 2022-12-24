package com.ehc.elastiknnSimilarityQuery;

import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.apache.lucene.search.Query;
import org.elasticsearch.common.ParseField;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class KnnQueryBuilder extends AbstractQueryBuilder<KnnQueryBuilder>{


    public static final String NAME = "elastiknn_nearest_neighbors";
    public static final String FIELD = "field";
    public static final String VALUE_FIELD_NAME = "values";
    public static final ParseField VEC = new ParseField("vec");
    private static String fieldName;
    private static Similarity similarity;
    private static String[] value;
    private static int candidates=50;
    private static int probes=2;


    /**
     * fiendName: elastiknn 으로 맵핑된 필드명
     * similarity: 사용할 유사도(cosine,l2, permutation_lsh,exact)
     */
    public KnnQueryBuilder(String fieldName,Similarity similarity, String[] value) {
        defaultKnnquery(fieldName, similarity, value);
    }

    private void defaultKnnquery(String fieldName, Similarity similarity, String[] value) {
        if (fieldName == null) {
            throw new IllegalArgumentException("[" + NAME + "] requires fieldName");
        }
        if (similarity == null) {
            throw new IllegalArgumentException("[" + NAME + "] requires similarity");
        }
        if (value == null) {
            throw new IllegalArgumentException("[" + NAME + "] requires query value");
        }
        this.similarity= similarity;
        this.fieldName = fieldName;
        this.value = value;
    }

    public KnnQueryBuilder(String fieldName,Similarity similarity, String[] value,int candidates) {
      defaultKnnquery(fieldName,similarity,value);
        if (candidates == 0) {
            throw new IllegalArgumentException("[" + NAME + "] requires candidates do not use zero");
        }
        this.candidates=candidates;
    }

    public KnnQueryBuilder(String fieldName,Similarity similarity, String[] value,int candidates,int probes) {
       defaultKnnquery(fieldName,similarity,value);
        if (candidates == 0) {
            throw new IllegalArgumentException("[" + NAME + "] requires candidates do not use zero");
        }
        if (probes == 0) {
            throw new IllegalArgumentException("[" + NAME + "] requires probes do not use zero");
        }
        this.candidates=candidates;
        this.probes=probes;
    }
    public KnnQueryBuilder(StreamInput in) throws IOException{
        super(in);
        fieldName=in.readString();
        similarity= in.readEnum(Similarity.class);
        value=in.readOptionalStringArray();
        candidates=in.readInt();
        probes=in.readInt();
    }
    @Override
    protected void doWriteTo(StreamOutput out) throws IOException {
        out.writeString(fieldName);
        out.writeEnum(similarity);
        out.writeGenericValue(value);
        out.writeInt(candidates);
        out.writeInt(probes);
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
    public int candidates(){return this.candidates;}
    public int probes(){return this.probes;}

    @Override
    protected void doXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(NAME);
        builder.field(FIELD,fieldName);
        builder.startObject(VEC.getPreferredName());
        builder.field(VALUE_FIELD_NAME, value);
        builder.endObject();
        Option option=new Option();
        option.setCandidates(candidates);
        option.setProbes(probes);
        buildBySimilarity(builder,similarity,option);
        builder.endObject();
    }
    protected void buildBySimilarity(XContentBuilder builder, Similarity similarity, Option option) throws IOException {
        Map<String, AbstractSimilarity> similarityMap= Similarity.getSimilarityMap();
        similarityMap.get(similarity.toString()).buildKnnQueryBySimilarity(builder,option);
    }

    @Override
    protected Query doToQuery(QueryShardContext context) throws IOException {
        return null;
    }

    @Override
    protected boolean doEquals(KnnQueryBuilder other) {
        return  Objects.equals(fieldName, other.fieldName) &&
                Objects.equals(value, other.value)&&
                Objects.equals(candidates, other.candidates)&&
                Objects.equals(probes, other.probes);
    }

    @Override
    protected int doHashCode() {
        return Objects.hash(fieldName, value,candidates,probes);
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
        Option option=new Option();
        return getQueryString(similarity, fieldName, from, size, vector, option);
    }

    public static String buildStringKnnQuery(Similarity similarity, String fieldName, int from, int size, String[] vector, int candidates)
    {
        Option option=new Option();
        option.setCandidates(candidates);
        return getQueryString(similarity, fieldName, from, size, vector, option);
    }
    public static String buildStringKnnQuery(Similarity similarity, String fieldName, int from, int size, String[] vector, int candidates,int probes)
    {
        Option option=new Option();
        option.setCandidates(candidates);
        option.setProbes(probes);
        return getQueryString(similarity, fieldName, from, size, vector, option);
    }
    private static String getQueryString(Similarity similarity, String fieldName, int from, int size, String[] vector, Option option) {
        Map<String, AbstractSimilarity> similarityMap= Similarity.getSimilarityMap();
        return similarityMap.get(similarity.toString()).queryForSimilarity(from, size, fieldName, vector, option);
    }

}
