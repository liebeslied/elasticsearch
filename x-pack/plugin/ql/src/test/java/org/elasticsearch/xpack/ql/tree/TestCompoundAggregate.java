/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */

package org.elasticsearch.xpack.ql.tree;

import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.function.aggregate.AggregateFunction;
import org.elasticsearch.xpack.ql.expression.function.aggregate.CompoundAggregate;
import org.elasticsearch.xpack.ql.type.DataType;

import java.util.List;

public class TestCompoundAggregate extends AggregateFunction implements CompoundAggregate {

    public TestCompoundAggregate(Source source, Expression field) {
        super(source, field);
    }

    @Override
    protected NodeInfo<TestCompoundAggregate> info() {
        return NodeInfo.create(this, TestCompoundAggregate::new, field());
    }

    @Override
    public TestCompoundAggregate replaceChildren(List<Expression> newChildren) {
        if (newChildren.size() != 1) {
            throw new IllegalArgumentException("expected [1] child but received [" + newChildren.size() + "]");
        }
        return new TestCompoundAggregate(source(), newChildren.get(0));
    }

    @Override
    public DataType dataType() {
        return DataType.DOUBLE;
    }
}
