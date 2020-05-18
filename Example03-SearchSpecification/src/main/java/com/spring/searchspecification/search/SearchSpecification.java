package com.spring.searchspecification.search;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearchSpecification<T> implements Specification<T> {
    private static final long serialVersionUID = 3316887183067645389L;

    public static class Builder<T> {

        private List<SearchCriterion<?>> searchCriteria;

        public Builder() {
            searchCriteria = new ArrayList<>();
        }

        /**
         * Adds another search condition
         *
         * @param field the column name in the table
         * @param type  the type of comparison
         * @param value the value to compare to
         * @param operand the value search
         * @param <S> reference data type for value
         * @return the Builder
         */
        public <S> Builder<T> and(String field, SearchType type, S value, SearchType operand) {
            searchCriteria.add(new SearchCriterion<S>(field, type, value, operand));
            return this;
        }

        /**
         * Constructor for adding search condition
         *
         * @param field
         * @param type
         * @param value
         * @param from
         * @param length
         * @param <T>
         * @return
         */
        public <T> Builder and(String field, SearchType type, T value, int from, int length, SearchType operand) {
            searchCriteria.add(new SearchCriterion<T>(field, type, value, from, length, operand));
            return this;
        }

        /**
         * Builds the spec from the builder
         *
         * @return set of SearchSpecification
         */
        public SearchSpecification<T> build() {
            return new SearchSpecification<T>(new ArrayList<>(searchCriteria));
        }
    }

    /**
     * The list of conditions
     */
    private List<SearchCriterion<?>> searchCriteria;


    private SearchSpecification(List<SearchCriterion<?>> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicatesAnd = new ArrayList<>();
        List<Predicate> predicatesOr = new ArrayList<>();

        if (searchCriteria.isEmpty()) {
            return null;
        }

        for (SearchCriterion<?> criterion : searchCriteria) {
            if(criterion.getOperand() == SearchType.OPERATOR_AND){
                predicatesAnd.add(buildPredicateFromCriterion(root,criteriaBuilder,criterion));
            }else if(criterion.getOperand() == SearchType.OPERATOR_OR){
                predicatesOr.add(buildPredicateFromCriterion(root,criteriaBuilder,criterion));
            }

        }

        if(!predicatesOr.isEmpty()) {
            Predicate orPredicate = criteriaBuilder.or(predicatesOr.toArray(new Predicate[predicatesOr.size()]));
            predicatesAnd.add(orPredicate);
        }
        return criteriaBuilder.and(predicatesAnd.toArray(new Predicate[predicatesAnd.size()]));
    }

    /**
     * Builds a predicate from the criterion provided.
     *
     * @param root      a reference to the entity type. (e.g. Root<T> implies we are
     *                  filtering rows from the table representing type T)
     * @param builder   used to build predicates and expressions from conditions
     * @param criterion the criterion defined by the builder
     * @return the Predicate
     */
    @SuppressWarnings("unchecked")
    private Predicate buildPredicateFromCriterion(Root<T> root, CriteriaBuilder builder,
                                                  SearchCriterion<?> criterion) {
        try {
            switch (criterion.getType()) {
                case EQUALS:
                    // Assuming the field is of type String appears to work here because it is
                    // translating both values (fetched and provided) to Strings, then performing
                    // the comparison. In this way, we do not need to know the type of the column
                    // before doing the comparison.
                    return builder.equal(getNormalizedField(root, criterion.getField()), criterion.getValue());

                case BETWEEN_DATES:
                    // Between requires SearchCriterion<Pair<LocalDateTime, LocalDateTime>>. We must
                    // type cast it.
                    Pair<LocalDateTime, LocalDateTime> typedCriterion = (Pair<LocalDateTime, LocalDateTime>) criterion
                            .getValue();
                    return builder.between(root.get(criterion.getField()), builder.literal(typedCriterion.getLeft()),
                            builder.literal(typedCriterion.getRight()));

                case IS_MEMBER:
                    return builder.isMember(criterion.getValue(),root.get(criterion.getField()));

                case IS_NOT_MEMBER:
                    return builder.isNotMember(criterion.getValue(), root.get(criterion.getField()));

                case NOT_EQUAL:
                    return builder.notEqual(getNormalizedField(root, criterion.getField()), criterion.getValue());

                case LIKE:
                    return builder.like(root.get(criterion.getField()), (String) criterion.getValue());
                case IS_NULL:
                    return builder.isNull(root.get(criterion.getField()));
                case SUBSTRING:
                    Expression<String> lSubString=builder.substring(root.<String>get(criterion.getField()),criterion.getFrom(),criterion.getLength());
                    return builder.equal(lSubString,criterion.getValue());


                default:
                    throw new IllegalArgumentException(
                            MessageFormat.format("Criterion has unimplemented type: {}", criterion.getType()));
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Criterion has incorrect value {} type for operation {} on column {}",
                            criterion.getValue(), criterion.getType(), criterion.getField()));
        }
    }

    /**
     * Normalizes field expression. Applied operations;
     * 1) [dot] notion for field name to add Path's as chain to support JOINed searches
     * NOTICE: This is only supported for {@link SearchType#EQUALS}
     *
     * @param root      a reference to the entity type. (e.g. Root<T> implies we are
     *                  filtering rows from the table representing type T)
     * @param fieldName the column name in the table
     * @return Expression<T>
     */
    private Expression<T> getNormalizedField(Root<T> root, final String fieldName) {
        if (fieldName.contains(".")) {
            Path<T> chain = null;
            for (String path : fieldName.split("\\.")) {
                if (chain == null) {
                    chain = root.get(path);
                } else {
                    chain = chain.get(path);
                }
            }
            return chain;
        } else {
            return root.get(fieldName);
        }
    }

}