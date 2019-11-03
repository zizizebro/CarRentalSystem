package mum.edu.cs.cs425.project.carrentalsystem.service.serviceimpl;

import mum.edu.cs.cs425.project.carrentalsystem.model.Credential;
import mum.edu.cs.cs425.project.carrentalsystem.model.Role;
import mum.edu.cs.cs425.project.carrentalsystem.repositary.CredentialRepository;
import mum.edu.cs.cs425.project.carrentalsystem.service.CredentialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("custom")
public class CredentialServiceImpl implements CredentialService {

    private static Logger logger = LoggerFactory.getLogger(CredentialServiceImpl.class);


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
    public Credential findByEmail(String email) {
        return credentialRepository.findByEmail(email);
    }

    @Override
    public List<Credential> findByEnabledTrue() {
        return credentialRepository.findByEnabledTrue();
    }

    @Override
    public List<Credential> findByEnabledFalse() {
        return credentialRepository.findByEnabledFalse();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info(email);
        Credential userAccount = credentialRepository.findByEmail(email);

        if(userAccount == null) throw new UsernameNotFoundException(email);

        List<Role> roles = userAccount.getRoles().stream().collect(Collectors.toList());

        User currentUser = new User(userAccount.getEmail(), userAccount.getPassword(), userAccount.isEnabled(), true,
                true, true, roles);

        System.out.println("CURRENT USER " + CredentialServiceImpl.class.getSimpleName());
        logger.info(currentUser.toString());
        return currentUser;
    }
}
