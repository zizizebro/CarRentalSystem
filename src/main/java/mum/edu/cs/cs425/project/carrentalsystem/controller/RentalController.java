package mum.edu.cs.cs425.project.carrentalsystem.controller;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import mum.edu.cs.cs425.project.carrentalsystem.model.*;
import mum.edu.cs.cs425.project.carrentalsystem.service.*;
import mum.edu.cs.cs425.project.carrentalsystem.util.EmailNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rental")
@SessionAttributes("car_id")
public class RentalController {

    private static Logger logger = LoggerFactory.getLogger(RentalController.class);

    @Autowired
    RentalService rentalService;

    @Autowired
    CarService carService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CredentialService credentialService;

    @Autowired
    CarStatusService carStatusService;

    @Autowired
    private EmailNotification emailNotification;

    @GetMapping("/book")
    public String bookCar(@RequestParam("car_id") Long carId, Model model){
        Car car = carService.findById(carId);


        model.addAttribute("rental", new Rental());
        model.addAttribute("car", car);
        model.addAttribute("car_id", carId);

        String carImage = recreateImage(car);
        model.addAttribute("carImage", carImage );

        return "/car/book";
    }

    @PostMapping("/book")
    public String bookCar(@ModelAttribute("rental") Rental rental, Principal principal,
                          HttpSession httpSession, RedirectAttributes redirectAttributes){

        logger.info(rental.toString());

        // Get selected car and update status to RENTED
        Car car = carService.findById((Long)httpSession.getAttribute("car_id"));
        car.setCarStatus(carStatusService.findById(2L));
        logger.info(rental.toString());


        // Get current customer
        Credential credential = credentialService.findByEmail(principal.getName());
        Customer customer = customerService.findByCredential(credential);


        rental.setCar(car);
        rental.setCustomer(customer);

        logger.info(rental.toString());
        rentalService.save(rental);

        // Remove selected car_id parameter from session object
        httpSession.invalidate();

        emailNotification.sendEmail(credential.getEmail(), "Car Booking Confirmation",
                "<p>This is confirmation email for your booking.</p>" +
                        "<p>Thank you for being loyal customer.</p>");

        redirectAttributes.addFlashAttribute("success", "Booking successful. Email confirmation sent.");
        return "redirect:/car/available/list";
    }


    private static String recreateImage(Car car) {
        String base64Encoded = null;

        try {
            byte[] encodeBase64 = Base64.encode(car.getCarPic()).getBytes();
            base64Encoded = new String(encodeBase64, "UTF-8");
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return base64Encoded;
    }
}

