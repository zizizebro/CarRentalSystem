package mum.edu.cs.cs425.project.carrentalsystem.repositary;

import mum.edu.cs.cs425.project.carrentalsystem.model.Credential;
import mum.edu.cs.cs425.project.carrentalsystem.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public Customer findByCredential(Credential credential);

}
