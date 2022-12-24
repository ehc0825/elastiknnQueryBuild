package com.ehc.elastiknnSimilarityQuery;

import java.util.Arrays;

public class ElastiknnSimilarQuery {

    /**
     * @param fieldName fieldNameMapping type ElastiKnn vector
     * @param from int
     * @param size int
     * @return elastiknn Simlarity Query FirstPart
     */
    public static String basedFrontQuery(String fieldName,int from, int size){
        String query = "{\n" +
                "\"from\":"+from+","+
                "\"size\":"+size+","+
                "  \"query\": {\n" +
                "    \"elastiknn_nearest_neighbors\": {\n" +
                "      \"field\": " +"\"" +
                fieldName+
                "\","+
                "\"vec\":{\n" +
                "          \"values\":";
        return query;
    }

    /**
     * elasticsearch search QueryForCosine
     * @return cosineQueryLastPart
     */
    public static String basedTailQueryForCosine(){
        String query=
                "      },\n" +
                "\"model\":\"lsh\","+
                "\"similarity\":\"angular\","+
                "\"candidates\":50"+
                "    }\n" +
                "  }\n" +
                "}";
        return query;
    }


    /**
     * elasticsearch search QueryForL2
     * @return L2QueryLastPart
     */
    public static String basedTailQueryForL2(){
        String query=
                "      },\n" +
                "\"model\":\"lsh\","+
                "\"similarity\":\"l2\","+
                "\"candidates\":50,"+
                "\"probes\":2"+
                "    }\n" +
                "  }\n" +
                "}";
        return query;
    }


    /**
     * elasticsearch search QueryForL2
     * @return Permutation_lshQueryLastPart
     */
    public static String basedTailQueryForPermutation_lsh(){
        String query=
                "      },\n" +
                "\"model\":\"permutation_lsh\","+
                "\"similarity\":\"angular\","+
                "\"candidates\":50"+
                "    }\n" +
                "  }\n" +
                "}";
        return query;
    }


    /**
     * elasticsearch search QueryForL2
     * @return Permutation_lshQueryLastPart
     */
    public static String basedTailQueryForExact(){
        String query=
                "      },\n" +
                        "\"model\":\"exact\","+
                        "\"similarity\":\"angular\""+
                        "    }\n" +
                        "  }\n" +
                        "}";
        return query;
    }

    /**
     * @param vectors String array for elastiknn vectorField
     * @return vector for searchElastiknn
     */
    public static String getVectorForQuery(String[] vectors){
        return Arrays.toString(vectors);
    }
}
