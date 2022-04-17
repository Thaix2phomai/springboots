package vn.techmaster.job_hunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.job_hunt.model.Applicant;
import vn.techmaster.job_hunt.repository.ApplicantRepo;
import vn.techmaster.job_hunt.repository.EmployerRepo;


@Controller
@RequestMapping("/applicant")
public class ApplicantController {
    @Autowired
    private ApplicantRepo listApplicant;

    @Autowired
    private EmployerRepo employerRepo;

   

    @GetMapping("/list")
    public String getAllJob(Model model) {
        model.addAttribute("applicants", listApplicant.getAll());
        return "applicants";
    }

    @GetMapping(value = "/add")
    public String addJobForm(Model model) {
        model.addAttribute("applicant", new Applicant("", "", "", "", "", "", "",""));
        model.addAttribute("employer", new String(""));
        model.addAttribute("employers", employerRepo.getAll());
       
        return "add_applicant";
    }

    @PostMapping(value = "/add")
    public String addJob(@ModelAttribute Applicant request, BindingResult bindingResult, Model model) {
        Applicant newApplicant = listApplicant.addApplicant(Applicant.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .skill_description(request.getSkill_description()).build());
        if (!bindingResult.hasErrors()) {

            return "redirect:/applicant/list";
        }
        return "add_applicant";

    }
    
}
