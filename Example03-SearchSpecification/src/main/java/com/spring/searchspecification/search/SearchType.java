package com.spring.searchspecification.search;

public enum SearchType {
    EQUALS,
    BETWEEN_DATES,
    IS_MEMBER,
    IS_NOT_MEMBER,
    NOT_EQUAL,
    LIKE,
    IS_NULL,
    OPERATOR_AND,
    OPERATOR_OR,
    SUBSTRING;

    private SearchType() {
    }
}
