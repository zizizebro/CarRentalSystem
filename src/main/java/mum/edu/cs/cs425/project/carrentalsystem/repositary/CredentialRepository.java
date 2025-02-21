package mum.edu.cs.cs425.project.carrentalsystem.repositary;

import mum.edu.cs.cs425.project.carrentalsystem.model.Credential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepository extends CrudRepository<Credential, Long> {
    Credential findByEmail(String email);
    List<Credential> findByEnabledTrue();
    List<Credential> findByEnabledFalse();
}
