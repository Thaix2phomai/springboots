package vn.techmasterr.jobhunt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmasterr.jobhunt.model.Employer;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    ConcurrentHashMap<String, Employer> employerList =new ConcurrentHashMap<>();
    public EmployerController(){
        employerList.put("01",new Employer("Thái", "thai123.com", "thaithedoublecheese1@gmail.com"," Hanoi"));
        employerList.put("02",new Employer("Cường", "cuongkaka11.com", "cuongnhinho@gmail.com"," HaiPhong"));
        employerList.put("03",new Employer("Dũng", "dungbeo4.com", "dungdung@gmail.com"," Hanoi"));
        employerList.put("04",new Employer("Tuấn", "tuanto4.com", "tuan2k4@gmail.com"," Hanoi"));
        employerList.put("05",new Employer("Khai", "giaosukhai.com", "khaibe@gmail.com","HoChiMinh"));
    }

    @GetMapping("/list")
    public String employersList(Model model) {
        var employers = employerList.values().stream().collect(Collectors.toList());
        model.addAttribute("employers", employers);
        return "employer/list";
    }

    @GetMapping("/add")
    public String employerForm(Model model){
        model.addAttribute("employer", new Employer());
        return "employer/add";
    }

    @PostMapping("/add")
    public String createEmployer(Model model, @ModelAttribute("employer") Employer employer){
        employerList.put(UUID.randomUUID().toString(), new Employer(employer.getName(),employer.getWebsite(),employer.getEmail(),employer.getAddress()));
        return "employer/list";
    }
}
