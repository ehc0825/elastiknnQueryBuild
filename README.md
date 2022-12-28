# elastiknnQueryBuild
## elastiknnQueryBuild

## Java RestHighLevelClient QueryBuilder
For ElastiKnn(https://elastiknn.com/)



## use in gradle

### add build.gradle

1. repositories
```
repositories {
	...
	maven { url 'https://jitpack.io' }
}
```
2. dependencies
```
dependencies {
	...
	implementation 'com.github.ehc0825:elastiknnQueryBuild:7.10.2-oss'
	...
}
```


## support
elasticsearch oss 7.5.2



## use this
1. 
```
KnnQueryBuilder(String fieldName,Similarity similarity, String[] value)
```
2. 

```
KnnQueryBuilder(String fieldName,Similarity similarity, String[] value,int candidates)
```
3. 

```
KnnQueryBuilder(String fieldName,Similarity similarity, String[] value,int candidates,int probes)
```


### [value]
fieldName : field Name type elastiknn_dense_float_vector   
similarity: similarity Type cosine, l2, permutation_lsh, exact   
value: vector   
candidates: int candidates (default 50)   
probes: int probes (default 2)   
 
