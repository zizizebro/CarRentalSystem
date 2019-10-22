package mum.edu.cs.cs425.project.carrentalsystem.controller;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import mum.edu.cs.cs425.project.carrentalsystem.model.Car;
import mum.edu.cs.cs425.project.carrentalsystem.model.CarStatus;
import mum.edu.cs.cs425.project.carrentalsystem.service.CarService;
import mum.edu.cs.cs425.project.carrentalsystem.service.CarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarStatusService carStatusService;

    @Autowired
    CarService carService;

    @GetMapping("/list")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "/car/list";
    }

    @GetMapping("/api/list")
    public @ResponseBody List<Car> allCarsApi(){
        return carService.findAll();
    }

    @GetMapping("/new")
    public String carForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("status", carStatusService.findAll());
        return "/car/new";
    }


    @PostMapping("/new")
    public String save(@Valid @ModelAttribute("car") Car car,
                       BindingResult result,
                       @RequestParam("carPic") MultipartFile image,
                       @RequestParam("carStatus") final CarStatus carStatus, Model model) {
        //

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            System.out.println(carStatus);
            System.out.println(result.getAllErrors().toString());
            model.addAttribute("status", carStatusService.findAll());
            return "car/new";
        }

        //read uploaded image as bytes and set image attribute of product entity
        if (!image.isEmpty()) {
            try {
                byte[] bytes = image.getBytes();
                car.setCarPic(bytes);
            } catch (Exception e) {
                System.out.println("You failed to upload  => " + e.getMessage());
            }
        }

        car.setCarStatus(carStatus);
        car = carService.save(car);

//        update with correct url
        return "redirect:/car/list";
    }
    @GetMapping(value="/available/list")
    public ModelAndView availableCars() {
        List<Car> availableCars = carService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("availableCars", availableCars);
        modelAndView.setViewName("car/rentallist");

        List<String> carImages = recreateImages(availableCars);
        modelAndView.addObject("carImages", carImages );

        return modelAndView;
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model theModel) {

        // get the car from the service
        Car theCar = carService.findById(id);

        // set Car as a model attribute to pre-populate the form
        theModel.addAttribute("car", theCar);
        theModel.addAttribute("status", carStatusService.findAll());

        // send over to our form
        return "car/new";
    }

    @GetMapping("car/delete")
    public String delete(@RequestParam("id") Long id) {

        // delete the Car
        carService.deleteById(id);

        // redirect to /car/list
        return "redirect:/car/list";

    }


    public static List<String> recreateImages(List<Car> cars) {
        List<String> carImages = new ArrayList<>();

        try {
            for (Car c : cars) {
                byte[] encodeBase64 = Base64.encode(c.getCarPic()).getBytes();
                String base64Encoded = new String(encodeBase64, "UTF-8");
                carImages.add(base64Encoded);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return carImages;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws ServletException {
        binder.registerCustomEditor(byte[].class,
                new ByteArrayMultipartFileEditor());
    }
}
