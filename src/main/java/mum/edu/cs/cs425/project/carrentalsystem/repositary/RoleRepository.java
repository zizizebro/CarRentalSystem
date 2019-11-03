package mum.edu.cs.cs425.project.carrentalsystem.repositary;

import mum.edu.cs.cs425.project.carrentalsystem.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    public Role findByRole(String role);
    public List<Role> findByRoleNot(String role);
}
