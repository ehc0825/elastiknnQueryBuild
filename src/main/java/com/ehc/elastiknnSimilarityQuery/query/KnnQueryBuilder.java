package com.ehc.elastiknnSimilarityQuery.query;

import com.ehc.elastiknnSimilarityQuery.Similarity_Type;
import com.ehc.elastiknnSimilarityQuery.similarities.dto.Option;
import org.apache.lucene.search.Query;
import org.elasticsearch.common.ParseField;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.*;

import java.io.IOException;
import java.util.Objects;

public class KnnQueryBuilder extends AbstractQueryBuilder<KnnQueryBuilder>{


    public static final String NAME = "elastiknn_nearest_neighbors";
    public static final String FIELD = "field";
    public static final String VALUE_FIELD_NAME = "values";
    public static final ParseField VEC = new ParseField("vec");
    private static String fieldName;
    private static Similarity_Type similarityType;
    private static String[] value;
    private static int candidates=50;
    private static int probes=2;


    /**
     * fiendName: elastiknn 으로 맵핑된 필드명
     * similarity: 사용할 유사도(cosine,l2, permutation_lsh,exact)
     */
    public KnnQueryBuilder(String fieldName, Similarity_Type similarityType, String[] value) {
        defaultKnnquery(fieldName, similarityType, value);
    }

    private void defaultKnnquery(String fieldName, Similarity_Type similarityType, String[] value) {
        if (fieldName == null) {
            throw new IllegalArgumentException("[" + NAME + "] requires fieldName");
        }
        if (similarityType == null) {
            throw new IllegalArgumentException("[" + NAME + "] requires similarity");
        }
        if (value == null) {
            throw new IllegalArgumentException("[" + NAME + "] requires query value");
        }
        this.similarityType = similarityType;
        this.fieldName = fieldName;
        this.value = value;
    }

    public KnnQueryBuilder(String fieldName, Similarity_Type similarityType, String[] value, int candidates) {
      defaultKnnquery(fieldName, similarityType,value);
        if (candidates == 0) {
            throw new IllegalArgumentException("[" + NAME + "] requires candidates do not use zero");
        }
        this.candidates=candidates;
    }

    public KnnQueryBuilder(String fieldName, Similarity_Type similarityType, String[] value, int candidates, int probes) {
       defaultKnnquery(fieldName, similarityType,value);
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
        similarityType = in.readEnum(Similarity_Type.class);
        value=in.readOptionalStringArray();
        candidates=in.readInt();
        probes=in.readInt();
    }
    @Override
    protected void doWriteTo(StreamOutput out) throws IOException {
        out.writeString(fieldName);
        out.writeEnum(similarityType);
        out.writeGenericValue(value);
        out.writeInt(candidates);
        out.writeInt(probes);
    }
    public String fieldName() {
        return this.fieldName;
    }
    public Similarity_Type similarity() {
        return this.similarityType;
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
        buildBySimilarity(builder, similarityType,option);
        builder.endObject();
    }
    protected void buildBySimilarity(XContentBuilder builder, Similarity_Type similarityType, Option option) throws IOException {
        similarityType.getAbstractSimilarity().buildKnnQueryBySimilarity(builder,option);
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



}
