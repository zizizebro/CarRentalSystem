package mum.edu.cs.cs425.project.carrentalsystem.service;



import mum.edu.cs.cs425.project.carrentalsystem.model.Role;

import java.util.List;

public interface RoleService {

    public Role save(Role role);
    public List<Role> findAll();
    public Role findById(Long id);
    public void deleteById(Long id);
    public Role findByRole(String role);
}
