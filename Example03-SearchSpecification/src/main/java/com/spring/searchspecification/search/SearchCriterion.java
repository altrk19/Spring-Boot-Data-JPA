package com.spring.searchspecification.search;

public class SearchCriterion<T> {
    private String field;
    private SearchType type;
    private T value;
    private SearchType operand;
    private int from;
    private int length;

    public SearchCriterion(String field, SearchType type, T value, SearchType operand) {
        this.field = field;
        this.type = type;
        this.value = value;
        this.operand = operand;
    }

    public SearchCriterion(String field, SearchType type, T value, int from, int length, SearchType operand) {
        this(field, type, value, operand);
        this.from = from;
        this.length = length;
    }

    public String getField() {
        return this.field;
    }

    public SearchType getType() {
        return this.type;
    }

    public T getValue() {
        return this.value;
    }

    public SearchType getOperand() {
        return this.operand;
    }

    public int getFrom() {
        return this.from;
    }

    public int getLength() {
        return this.length;
    }
}
