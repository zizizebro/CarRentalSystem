package mum.edu.cs.cs425.project.carrentalsystem.controller;

import mum.edu.cs.cs425.project.carrentalsystem.model.Customer;
import mum.edu.cs.cs425.project.carrentalsystem.model.Role;
import mum.edu.cs.cs425.project.carrentalsystem.service.CustomerService;
import mum.edu.cs.cs425.project.carrentalsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/list")
    public String getAllCustomer(Model model){
        List<Customer> customers = (List<Customer>) customerService.findAll();
        model.addAttribute("customers", customers);

        return "customer/customerList";
    }

    @GetMapping("/register")
    public String customerForm(Model model){

        model.addAttribute("customer", new Customer());
        return "customer/new";
    }

//    @GetMapping("/save")
//    public String getSave(Model theModel){
//        theModel.addAttribute("customers",new Customer());
//        return "customer/newCustomer";
//    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/customer/new";
        }
        customer.getCredential().setPassword(bCryptPasswordEncoder.encode(customer.getCredential().getPassword()));
        Role customerRole = roleService.findByRole("CUSTOMER");
        Set<Role> roles = new HashSet<>();
        roles.add(customerRole);
        customer.getCredential().setRoles(roles);

        customerService.save(customer);

        return "redirect:/login";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model theModel) {

        // get the customer from the service
        Customer customer = customerService.findById(id);

        // set customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer", customer);

        // send over to our form
        return "customer/newCustomer";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        // delete the employee
        customerService.deleteById(id);

        // redirect to /employees/list
        return "redirect:/cust/list";

    }
}
