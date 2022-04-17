package vn.techmaster.job_hunt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Applicant {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String skill_description;
    private String choose_company;
    private String choose_job;
    private String choose_city;
}
