package naayadaa.search;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE, STARTS_WITH, ENDS_WITH, CONTAINS;


    public static final String OR_OPERATOR = "OR";

    public static final String AND_OPERATOR = "AND";

}
