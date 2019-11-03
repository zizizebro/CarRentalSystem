package mum.edu.cs.cs425.project.carrentalsystem.controller;


import mum.edu.cs.cs425.project.carrentalsystem.model.Credential;
import mum.edu.cs.cs425.project.carrentalsystem.model.Role;
import mum.edu.cs.cs425.project.carrentalsystem.service.CredentialService;
import mum.edu.cs.cs425.project.carrentalsystem.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    CredentialService credentialService;

    @Autowired
    RoleService roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/account/list")
    public String allAccounts(Model model){
        model.addAttribute("accounts", credentialService.findAll());
        return "account/list";
    }

    @GetMapping("/account/new")
    public String accountPage(@ModelAttribute("credential")Credential credential, Model model){
        logger.info("Insded new account form handler");
        model.addAttribute("roles", roleService.findByRoleNot("CUSTOMER"));
        logger.info("ALL ROLES ******* " + roleService.findByRoleNot("CUSTOMER"));
        return "account/new";
    }

    @PostMapping("/account/new")
    public String saveAccount(@Valid @ModelAttribute("credential")Credential credential,
                              BindingResult bindingResult, @RequestParam("role") Role role, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("roles", roleService.findAll());
            return "account/new";
        }

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        credential.setRoles(roles);

        // Encrypt password
        credential.setPassword(bCryptPasswordEncoder.encode(credential.getPassword()));

        credentialService.save(credential);

        return "redirect:/admin/account/list";
    }

    @GetMapping("/account/update")
    public String update(@RequestParam("id") Long id, Model model){
        model.addAttribute("credential", credentialService.findById(id));
        model.addAttribute("roles", roleService.findByRoleNot("CUSTOMER"));
        return "account/new";
    }

    @GetMapping("/account/delete")
    public String delete(@RequestParam("id") Long id, Model model){
        credentialService.deleteById(id);

        model.addAttribute("accounts", credentialService.findAll());

        return "account/list";
    }

    @GetMapping("/deactivate")
    public String getDeactivatedAccounts(Model model){

        model.addAttribute("activeAccounts", credentialService.findByEnabledTrue());

        return "account/deactivate";
    }

    @GetMapping("/account/deactivate")
    public String deactivatedAccount(@RequestParam("id") Long id, Model model){
        Credential credential = credentialService.findById(id);
        credential.setEnabled(false);
        credentialService.save(credential);

        model.addAttribute("activeAccounts", credentialService.findByEnabledTrue());
        return "account/deactivate";
    }

    @GetMapping("/activate")
    public String getActivatedAccounts(Model model){

        model.addAttribute("inActiveAccounts", credentialService.findByEnabledFalse());

        return "account/activate";
    }

    @GetMapping("/account/activate")
    public String activatedAccount(@RequestParam("id") Long id, Model model){

        Credential credential = credentialService.findById(id);
        credential.setEnabled(true);
        credentialService.save(credential);

        model.addAttribute("inActiveAccounts", credentialService.findByEnabledFalse());
        return "account/activate";
    }

}
