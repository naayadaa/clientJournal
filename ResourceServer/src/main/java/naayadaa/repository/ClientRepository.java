package naayadaa.repository;

import naayadaa.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
public interface ClientRepository extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {
}
