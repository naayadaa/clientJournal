package naayadaa.search;

import naayadaa.model.Client;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
public final class ClientSpecificationsBuilder {

    private final List<SpecSearchCriteria> params;

    public ClientSpecificationsBuilder() {
        params = new ArrayList<>();
    }


    public Specification<Client> build() {

        if (params.size() == 0)
            return null;

        Specification<Client> result = new ClientSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specifications.where(result)
                    .or(new ClientSpecification(params.get(i)))
                    : Specifications.where(result)
                    .and(new ClientSpecification(params.get(i)));

        }

        return result;
    }

    public final ClientSpecificationsBuilder with(ClientSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final ClientSpecificationsBuilder with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
