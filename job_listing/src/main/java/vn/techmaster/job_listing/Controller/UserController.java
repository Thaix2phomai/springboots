package vn.techmaster.job_listing.Controller;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.job_listing.Model.Job;
import vn.techmaster.job_listing.dto.JobRequest;

@RestController
@RequestMapping("/job")
public class UserController {
    private ConcurrentHashMap<String, Job> jobs;

    public UserController() {
        jobs = new ConcurrentHashMap<>();
        jobs.put("AAAZ-01", new Job("AAAZ-01", "Tuyển dụng Dev Spingboots", "Yêu cầu lập trình viên > 3 năm kinh nghiệm",
                "Hà Nội", 8000000, 16000000, "sphinx11@gmail.com"));
        jobs.put("BBBZ-02", new Job("BBBZ-02", "Tuyển dụng Dev iOS", "Yêu cầu lập trình viên iOS > 4 năm kinh nghiệm",
                "Hồ Chí Minh", 9000000, 17000000, "amazon@gmail.com"));
        jobs.put("CCCZ-03", new Job("CCCZ-03", "Tuyển dụng Dev NodeJS", "Yêu cầu lập trình viên NodeJS > 1 năm kinh nghiệm",
                "Hải Phòng", 20000000, 29000000, "microsoft@gmail.com"));
    }

  //   @GetMapping
  //   public List<Job> getJobs() {
  //       return jobs.values().stream().toList();
  //   }

  //   @PostMapping
  //   public Job createNewJob(@RequestBody JobRequest jobRequest) {
  //       String uuid = UUID.randomUUID().toString();
  //       Job newJob = new Job(uuid, jobRequest.title(), jobRequest.description(), jobRequest.location(),
  //       jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
  //       jobs.put(uuid, newJob);
  //       return newJob;
  //   }

  //   @GetMapping(value = "/{id}")
  //   public Job getJobById(@PathVariable("id") String id) {
  //       return jobs.get(id);
  //   }

  //   @PutMapping(value="/{id}")
  //   public Job updateBookById(@PathVariable("id") String id, @RequestBody JobRequest jobRequest) {
  //   Job updateJob = new Job(id, jobRequest.title(), jobRequest.description(), jobRequest.location(), jobRequest.min_salary(),jobRequest.max_salary(),jobRequest.email_to());
  //   //books.put(id, updateBook);
  //   jobs.replace(id, updateJob);
  //   return updateJob;
  // }

  // @DeleteMapping(value="/{id}")
  // public Job deleteJobById(@PathVariable("id") String id) {
  //   Job removedJob = jobs.remove(id);
  //   return removedJob;
  // }


  // @GetMapping(value = "/sortbyid")
  // public List<Job> sortByID(){
  //   return jobs.values().stream().sorted(Comparator.comparing(Job::getId)).collect(Collectors.toList());
  // }

  // @GetMapping(value = "/sortbyminsalary")
  // public List<Job> sortByminSalary(){
  //   return jobs.values().stream().sorted(Comparator.comparing(Job::getMin_salary).reversed()).collect(Collectors.toList());
  // }
 
  // @GetMapping(value = "/sortbytitle")
  // public List<Job> sortByTitle(){
  //   return jobs.values().stream().sorted(Comparator.comparing(Job::getTitle)).collect(Collectors.toList());
  // }
  //Bài 1
  @GetMapping(value = "/sortbylocation")
  public List<Job> sortByLocation(){
   return jobs.values().stream().sorted(Comparator.comparing(Job::getLocation)).collect(Collectors.toList());
  }
  //Bài 2
  @GetMapping(value = "/salary/{salary}")
  public List<Job> getJobBySalary(@PathVariable("salary") long salary) {
      return jobs.values().stream().filter(i -> (i.getMin_salary() <= (salary)) && (i.getMax_salary() >= (salary)))
              .collect(Collectors.toList());
  }
  //Bài 3
  @GetMapping(value = "/keyword/{keyword}")
  public List<Job> getListByKeyword(@PathVariable("keyword") String keyword) {
      return jobs.values().stream()
              .filter(i -> (i.getTitle().toLowerCase().contains(keyword.toLowerCase())) || (i.getDescription().toLowerCase().contains(keyword.toLowerCase())))
              .collect(Collectors.toList());
  }
  //Bài 4
  @GetMapping(value = "/query")
  public List<Job> getListByLocationAndKeyword(@RequestParam("location") String location,
          @RequestParam("keyword") String keyword) {
      return jobs.values().stream()
              .filter(i -> ((i.getTitle().contains(keyword)) || (i.getDescription().contains(keyword)))
                      && (i.getLocation().contains(location)))
              .collect(Collectors.toList());
  }

}
