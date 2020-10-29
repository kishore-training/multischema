package com.udemy.multischema.service;

import com.udemy.multischema.model.Hospital;
import com.udemy.multischema.tenant.TenantAwareService;
import com.udemy.multischema.utils.HospitalNotFoundException;
import com.udemy.multischema.vo.HospitalRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@EnableSwagger2
@Controller
public class HospitalController {


    @Autowired
    HospitalControllerService hospitalControllerService;



    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("hospitalRequestVO", new HospitalRequestVO());
        return "registerHospital";
    }

    @GetMapping("/home")
    public String showHomeForm(Model model) {
        model.addAttribute("hospitalRequestVO", new HospitalRequestVO());
        return "home";
    }

    @GetMapping("/login")
    public String showloginForm(Model model) {
        model.addAttribute("hospitalRequestVO", new HospitalRequestVO());
        return "login";
    }

    /**
     * View the list of registered hospitals from the ADMIN tenant schema
     * @param model
     * @return
     */
    @GetMapping(value = "/hospital")
    public String listHospitals(Model model) {
        TenantAwareService.setTenant(TenantAwareService.ADMIN_TENANT);
        List<Hospital> hospitals = hospitalControllerService.listHospitals();
        String user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("hospital", hospitals);
        model.addAttribute("user", user);
        model.addAttribute("message","");
        return "listHospitals";
    }

    /**
     * Register the hospital in ADMIN tenant, create a seperate tenant schema and create the hospital in tenant schema and and create
     * the tenant admin user
     * @param hospitalTenantId
     * @param hospitalRequestVO
     * @param result
     * @param model
     * @param response
     * @return
     */
    @PostMapping(value = "/hospital/register")
    public String createHospital(@CookieValue(value = TenantAwareService.TENANT_COOKIE_NAME) String hospitalTenantId,@ModelAttribute @Valid HospitalRequestVO hospitalRequestVO, BindingResult result, Model model, HttpServletResponse response) {
        ResponseEntity<Hospital> resp = null;

        Hospital hospital = null;
        if (result.hasErrors()) {
            return "registerHospital";
        }
        try {
            hospital = hospitalControllerService.registerHospitalDetails(hospitalRequestVO);
        } catch (Exception e) {
            model.addAttribute("message", "Failure in Register Hospital" + e.getMessage());
            return "failureMessage";
        }
        model.addAttribute("hospitalName", hospital.getHospitalName());

        return "registerSuccess";
    }


    /**
     * Delete the hospital from the registered hospital in ADMIN TENANT schema. Delete the tenant schema manually after this
     * @param hospitalName
     * @param model
     * @return
     */
    @GetMapping(value = "/hospital/delete")
    public String deleteOrganization(@RequestParam String hospitalName, Model model) {
        List<Hospital> hospitals = null;
        try {
            TenantAwareService.setTenant(TenantAwareService.ADMIN_TENANT);
            hospitalControllerService.deleteHospital(hospitalName);
            hospitals = hospitalControllerService.listHospitals();
            String user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            model.addAttribute("hospital", hospitals);
            model.addAttribute("user", user);
            model.addAttribute("message", "Deleted the hospital. Please delete the tenant based schema manually");
        } catch (HospitalNotFoundException e) {
            model.addAttribute("message", "Failure in Delete Hospital" + e.getMessage());
            return "failureMessage";
        }
        return "redirect:/hospital";

    }

}
