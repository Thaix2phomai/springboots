package vn.techmaster.job_hunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.job_hunt.model.Job;
import vn.techmaster.job_hunt.repository.EmployerRepo;
import vn.techmaster.job_hunt.repository.JobRepo;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobRepo listJob;

    @Autowired
    private EmployerRepo employerRepo;

    @GetMapping("/list")
    public String getAllJob(Model model) {
        model.addAttribute("jobs", listJob.getAll());
        return "jobs";
    }

    @GetMapping(value = "/add")
    public String addJobForm(Model model) {
        model.addAttribute("job", new Job("", "", "", "", ""));
        model.addAttribute("employer", new String(""));
        model.addAttribute("employers", employerRepo.getAll());
        return "add_job";
    }

    @PostMapping(value = "/add")
    public String addJob(@ModelAttribute Job request, BindingResult bindingResult, Model model) {
        Job newJob = listJob.addJob(Job.builder()
                .title(request.getTitle())
                .emp_id(request.getEmp_id())
                .description(request.getDescription())
                .city(request.getCity()).build());
        if (!bindingResult.hasErrors()) {

            return "redirect:/job/list";
        }
        return "add_job";

    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable String id) {
        return "redirect:/job/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteEmployerByID(@PathVariable String id) {
        Job job = listJob.deleteById(id);
        return "redirect:/job/list";
    }


}
