package mum.edu.cs.cs425.project.carrentalsystem.controller;

import mum.edu.cs.cs425.project.carrentalsystem.model.CarStatus;
import mum.edu.cs.cs425.project.carrentalsystem.service.CarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/status")
public class CarStatusController {
    @Autowired
    private CarStatusService carStatusService;


    @GetMapping("/list")

    public String getAllCarStatus(Model model){
        List<CarStatus> carStatuses = carStatusService.findAll();
        model.addAttribute("carStatuses", carStatuses);
        return "status/carStatusList";
    }

    @GetMapping("/register")
    public String employeeForm(Model model){

        model.addAttribute("carStatuses", new CarStatus());
        return "status/newCarStatus";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("carStatuses") CarStatus carStatuses){
        carStatusService.save(carStatuses);
        return "redirect:/status/list";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("statusId") Long id, Model theModel) {

        // get the employee from the service
        CarStatus theEmployee = carStatusService.findById(id);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("carStatuses", theEmployee);

        // send over to our form
        return "status/newCarStatus";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("statusId") Long id) {

        // delete the employee
        carStatusService.deleteById(id);

        // redirect to /employees/list
        return "redirect:/status/list";

    }
}
