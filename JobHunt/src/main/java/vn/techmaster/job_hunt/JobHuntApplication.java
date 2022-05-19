package vn.techmaster.job_hunt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.techmaster.job_hunt.model.*;
import vn.techmaster.job_hunt.repository.ApplicantRepository;
import vn.techmaster.job_hunt.repository.EmployerRepository;
import vn.techmaster.job_hunt.repository.JobRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.UUID;

@SpringBootApplication
public class JobHuntApplication implements CommandLineRunner {

	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private JobRepository jobRepository;


	@Autowired
	private ApplicantRepository applicantRepository;

	public static void main(String[] args) {
		SpringApplication.run(JobHuntApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String id = UUID.randomUUID().toString();
//		Employer employer = new Employer("1", "FPT", "fpt.png", "fpt.com.vn", "bdoremonllk@gmail.com");

		Employer employer = Employer.builder().id("1").name("FPT").website("https://fpt.com.vn").email("fpt.com.vn").logo_path("fpt.png").build();
		Employer employer1 = Employer.builder().id("2").name("Google").website("https://google.com.vn").email("google.com.vn").logo_path("google.png").build();
		Employer employer2 = Employer.builder().id("3").name("AmaZon").website("https://amazon.com.vn").email("amazon.com.vn").logo_path("amazon.png").build();


		employerRepository.save(employer);
		employerRepository.save(employer1);
		employerRepository.save(employer2);
//		Job job = new Job("1", "Fullstack Java Developer", "Remote fulltime", City.HaNoi, LocalDateTime.now(), LocalDateTime.now(), employer);
//		Job job1 = new Job("2", "Fullstack C# Developer", "Remote fulltime", City.HaNoi, LocalDateTime.now(), LocalDateTime.now(), employer);

		Job job = Job.builder().id("1").title("Fullstack Java Developer").description("fulltime").city(City.HaNoi).create_at(LocalDateTime.now()).update_at(LocalDateTime.now()).employer(employer).build();
		Job job1 = Job.builder().id("2").title("Fullstack C++ Developer").description("fulltime").city(City.DaNang).create_at(LocalDateTime.now()).update_at(LocalDateTime.now()).employer(employer1).build();
		Job job2 = Job.builder().id("3").title("Fullstack Python Developer").description("fulltime").city(City.HaiPhong).create_at(LocalDateTime.now()).update_at(LocalDateTime.now()).employer(employer2).build();
		Job job3 = Job.builder().id("4").title("Fullstack JS Developer").description("fulltime").city(City.HoChiMinh).create_at(LocalDateTime.now()).update_at(LocalDateTime.now()).employer(employer).build();


		jobRepository.save(job);
		jobRepository.save(job1);
		jobRepository.save(job2);
		jobRepository.save(job3);

		ArrayList<Skill> arr1 = new ArrayList<>();
		arr1.add(Skill.AWS);
		arr1.add(Skill.Java);

		ArrayList<Skill> arr2 = new ArrayList<>();
		arr2.addAll(EnumSet.allOf(Skill.class));

		Applicant applicant = Applicant.builder().id("1").name("Thaicheese").email("thai123@gmai.com").phone("0989980117").skills(arr1).job(job).build();
		Applicant applicant1 = Applicant.builder().id("2").name("Thaicheese2").email("thai1234@gmai.com").phone("0913248977").skills(arr2).job(job1).build();


//		Applicant applicant = new Applicant("1","Nakamura01","nakamura01@gmail.com","0977342466",new ArrayList<Skill>(EnumSet.allOf(Skill.class)),job);
//		Applicant applicant1 = new Applicant("2","Nakamura02","nakamura02@gmail.com","0977342466",new ArrayList<Skill>(EnumSet.allOf(Skill.class)),job1);
		applicantRepository.save(applicant);
		applicantRepository.save(applicant1);
	}
}
