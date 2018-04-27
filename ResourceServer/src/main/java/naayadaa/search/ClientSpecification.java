package naayadaa.search;

import naayadaa.model.Client;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
public class ClientSpecification implements Specification<Client> {

    private SpecSearchCriteria criteria;

    public ClientSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SpecSearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(final Root<Client> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getField()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getField()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getField()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getField()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(criteria.getField()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.get(criteria.getField()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getField()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.get(criteria.getField()), "%" + criteria.getValue() + "%");
            default:
                return null;
        }
    }

}