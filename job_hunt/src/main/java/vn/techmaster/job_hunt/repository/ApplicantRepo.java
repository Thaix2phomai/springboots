package vn.techmaster.job_hunt.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import vn.techmaster.job_hunt.model.Applicant;

@Repository
public class ApplicantRepo {
    private ConcurrentHashMap<String, Applicant> listApplicant = new ConcurrentHashMap<>();

    public ApplicantRepo() {

    }

    public Collection<Applicant> getAll() {
        return listApplicant.values();
    }

    public Applicant addApplicant(Applicant applicant) {
        String id = UUID.randomUUID().toString();
        applicant.setId(id);
        listApplicant.put(id, applicant);
        return applicant;
    }

    public Applicant findApplicantById(String id) {
        return listApplicant.get(id);
    }

    public Applicant deleteById(String id) {
        return listApplicant.remove(id);
    }

    public void update(Applicant applicant) {
        listApplicant.put(applicant.getId(), applicant);
    }

    @PostConstruct
    public void addSomeData() {
        this.addApplicant(Applicant.builder()
                .name("Nguyễn Văn a")
                .email("nguyenvana@gmail.com")
                .phoneNumber("0989980145")
                .skill_description("2 năm kinh nghiệm lập trình front-end")
                .choose_company("FPT")
                .choose_job("Front-end")
                .choose_city("HaNoi")
                .build());
        this.addApplicant(Applicant.builder()
                .name("Nguyễn Văn B")
                .email("nguyenvanb@gmail.com")
                .phoneNumber("0394106477")
                .skill_description("3 năm làm việc tại techmaster, có kinh nghiệm làm back-end")
                .choose_company("CMC")
                .choose_job("Back-end")
                .choose_city("HoChiMinh")
                .build());
        this.addApplicant(Applicant.builder()
                .name("Nguyễn Văn c")
                .email("nguyenvanc@gmail.com")
                .phoneNumber("0989914577")
                .skill_description("Có kĩ năng làm PM tại công ty ABC 3 năm")
                .choose_company("Amazon")
                .choose_job("Project manager")
                .choose_city("HaNoi")
                .build());
    }
}
