package mum.edu.cs.cs425.project.carrentalsystem.controller;

import mum.edu.cs.cs425.project.carrentalsystem.model.Car;
import mum.edu.cs.cs425.project.carrentalsystem.model.CarStatus;
import mum.edu.cs.cs425.project.carrentalsystem.model.Customer;
import mum.edu.cs.cs425.project.carrentalsystem.model.Role;
import mum.edu.cs.cs425.project.carrentalsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/new")
    public String roleForm(Model model) {
        model.addAttribute("roles", new Role());

        return "/roles/new";
    }

    @PostMapping("/new")
    public String saveRole(@ModelAttribute("roles") Role role) {
        roleService.save(role);
        return "redirect:/roles/list";
    }

    @GetMapping("/list")
    public String getAllCustomer(Model model){
        List<Role> roles = (List<Role>) roleService.findAll();
        model.addAttribute("roles", roles);

        return "roles/list";
    }


    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model theModel) {

        // get the employee from the service
        Role theRole = roleService.findById(id);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("roles", theRole);

        // send over to our form
        return "roles/new";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        // delete the employee
        roleService.deleteById(id);

        // redirect to /employees/list
        return "redirect:/roles/list";

    }




}
