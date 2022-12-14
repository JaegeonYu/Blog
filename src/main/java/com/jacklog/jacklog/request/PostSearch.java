package com.jacklog.jacklog.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

import static java.lang.Math.*;

@Getter
@Setter
@Builder
public class PostSearch {
    private static final int MAX_SIZE = 2000;
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;

    public long getOffset(){
        return (long)(max(page,1) - 1) * min(MAX_SIZE, size);
    }

}
