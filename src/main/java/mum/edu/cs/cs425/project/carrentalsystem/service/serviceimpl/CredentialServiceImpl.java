package mum.edu.cs.cs425.project.carrentalsystem.service.serviceimpl;

import mum.edu.cs.cs425.project.carrentalsystem.model.Credential;
import mum.edu.cs.cs425.project.carrentalsystem.model.Role;
import mum.edu.cs.cs425.project.carrentalsystem.repositary.CredentialRepository;
import mum.edu.cs.cs425.project.carrentalsystem.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("custom")
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public Credential save(Credential credential) {
        return credentialRepository.save(credential);
    }

    @Override
    public List<Credential> findAll() {
        return (List<Credential>)credentialRepository.findAll();
    }

    @Override
    public Credential findById(Long id) {
        return credentialRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        credentialRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Credential userAccount = credentialRepository.findByEmail(email);

        if(userAccount == null) throw new UsernameNotFoundException(email);

        List<Role> roles = userAccount.getRoles().stream().collect(Collectors.toList());

        return new User(userAccount.getEmail(), userAccount.getPassword(), userAccount.isEnabled(), true,
                true, true, roles);
    }
}
