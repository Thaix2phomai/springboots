package vn.techmaster.job_hunt.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Person {
    @Size(min = 5 , max = 30 , message = "Name must between 5 and 30")
    private String name;

    @NotBlank(message = "Email cannot null")
    @Email(message = "Not valid email")
    private String email;
     MultipartFile logo;
}
