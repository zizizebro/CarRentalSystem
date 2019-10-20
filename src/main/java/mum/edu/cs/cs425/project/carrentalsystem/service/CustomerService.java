package mum.edu.cs.cs425.project.carrentalsystem.service;



import mum.edu.cs.cs425.project.carrentalsystem.model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer save(Customer customer);
    public List<Customer> findAll();
    public Customer findById(Long id);
    public void deleteById(Long id);
}
