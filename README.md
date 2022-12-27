# elastiknnQueryBuild
elastiknnQueryBuild

Java RestHighLevelClient QueryBuilder
For ElastiKnn(https://elastiknn.com/)



use in gradle

add build.gradle
repositories {
	...
	maven { url 'https://jitpack.io' }
}

dependencies {
	...
	implementation 'com.github.ehc0825:elastiknnQueryBuild:7.10.2-oss'
	...
}


elasticsearch oss 7.10.2


KnnQueryBuilder(String fieldName,Similarity similarity, String[] value)


KnnQueryBuilder(String fieldName,Similarity similarity, String[] value,int candidates)


KnnQueryBuilder(String fieldName,Similarity similarity, String[] value,int candidates,int probes)



[value]

fieldName : field Name type elastiknn_dense_float_vector
similarity: similarity Type cosine, l2, permutation_lsh, exact
value: vector
candidates: int candidates (default 50)
probes: int probes (default 2)
