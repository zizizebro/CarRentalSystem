package mum.edu.cs.cs425.project.carrentalsystem.service;




import mum.edu.cs.cs425.project.carrentalsystem.model.Credential;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CredentialService extends UserDetailsService {

    public Credential save(Credential credential);
    public List<Credential> findAll();
    public Credential findById(Long id);
    public void deleteById(Long id);
}
