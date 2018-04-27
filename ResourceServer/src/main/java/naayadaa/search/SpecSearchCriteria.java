package naayadaa.search;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
public class SpecSearchCriteria {

    private String field;
    private SearchOperation operation;
    private Object value;
    private boolean orPredicate;

    public SpecSearchCriteria() {

    }

    public SpecSearchCriteria(final String field, final SearchOperation operation, final Object value) {
        this.field = field;
        this.operation = operation;
        this.value = value;
    }

    public SpecSearchCriteria(final boolean orPredicate, final String field, final SearchOperation operation, final Object value) {
        this.orPredicate = orPredicate;
        this.field = field;
        this.operation = operation;
        this.value = value;
    }


    public String getField() {
        return field;
    }

    public void setField(final String field) {
        this.field = field;
    }

    public SearchOperation getOperation() {
        return operation;
    }

    public void setOperation(final SearchOperation operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public boolean isOrPredicate() {
        return orPredicate;
    }

    public void setOrPredicate(boolean orPredicate) {
        this.orPredicate = orPredicate;
    }

}