package com.ehc.elastiknnSimilarityQuery;

public enum SimilarityOption {
    COSINE{
        @Override
        public String toString() {
            return "cosine";
        }
    },
    L2{
        @Override
        public String toString() {
            return "l2";
        }
    },
    PERMUTATION_LSH{
        @Override
        public String toString() {
            return "permutation_lsh";
        }
    }

}
