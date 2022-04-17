package vn.techmaster.job_hunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.job_hunt.model.Employer;
import vn.techmaster.job_hunt.repository.EmployerRepo;
import vn.techmaster.job_hunt.request.EmployerRequest;
import vn.techmaster.job_hunt.service.StorageService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerRepo employerRepo;

    @Autowired
    private StorageService storageService;

    @GetMapping
    public String listAllEmplyer(Model model) {
        model.addAttribute("employers", employerRepo.getAll());
        return "employers";
    }

    @GetMapping(value = "/{id}")
    public String getEmployerById(Model model, @PathVariable String id) {
        model.addAttribute("employer", employerRepo.findById(id));
        return "employer";
    }

    @GetMapping(value = "/add")
    public String addEmployerForm(Model model) {
        model.addAttribute("employer", new EmployerRequest("", "", "", "", null));
        return "add_employer";
    }

    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    // consumes
    public String addEmployer(@Valid @ModelAttribute("employer") EmployerRequest employerRequest,
            BindingResult result, Model model) {
        if (employerRequest.logo().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("employerRequest", "photo", "Logo is required"));
        }

        // Nếu có lỗi thì trả về trình duyệt
        if (result.hasErrors()) {
            System.out.println("ERROR" + result.toString());
            return "add_employer";
        }

        // Thêm vào database

        Employer newEmployer = employerRepo.add(Employer.builder()
                .name(employerRequest.name())
                .website(employerRequest.website())
                .email(employerRequest.email())
                .build());

        // Lưu ảnh
        try {
            String saveLogoFileName = storageService.saveFile(employerRequest.logo(), newEmployer.getId());
            employerRepo.updateLogo(newEmployer.getId(), saveLogoFileName);
        } catch (IOException e) {
            // Nếu lưu file bị lỗi thì xóa bản ghi Employer
            e.printStackTrace();
        }

        return "redirect:/employer";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable String id) {
        return "redirect:/employer";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteEmployerByID(@PathVariable String id) {
        Employer emp = employerRepo.deleteById(id);
        storageService.deleteFile(emp.getLogo_path());
        return "redirect:/employer";
    }
}
