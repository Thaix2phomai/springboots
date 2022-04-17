package vn.techmaster.job_hunt.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.job_hunt.model.Job;


import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

@Repository
public class JobRepo {
    private ConcurrentHashMap<String, Job> listJob = new ConcurrentHashMap<>();

    public JobRepo(){

    }

    public Collection<Job> getAll(){
        return listJob.values();
    }

    public Job addJob(Job job){
        String id = UUID.randomUUID().toString();
        job.setId(id);
        listJob.put(id,job);
        return job;
    }

    public Job findJobById(String id){
        return listJob.get(id);
    }

    public Job deleteById(String id){
        return listJob.remove(id);
    }

    public void update(Job job){
        listJob.put(job.getId(), job);
    }

    @PostConstruct
    public void addSomeData(){
        
        this.addJob(Job.builder()
        .title("Front-end")
        .emp_id("FPT")
        .description("Thành thạo HTML, CSS, Boostrap và ngôn ngữ lập trình JavaScript")
        .city("HaNoi")
        .build());

        this.addJob(Job.builder()
        .title("Back-end")
        .emp_id("CMC")
        .description("Lập trình và đảm bảo tính khả thi của các thiết kế giao diện người dùng")
        .city("HoChiMinh")
        .build());

        this.addJob(Job.builder()
        .title("Devops")
        .emp_id("Google")
        .description("Có kinh nghiệm với các điện toán đám mây (AWS, Azure, GCP) để triển khai các bản nâng cấp và sửa lỗi")
        .city("DaNang")
        .build());

        this.addJob(Job.builder()
        .title("Project Manager")
        .emp_id("Amazon")
        .description("Quản lý dự án thành công")
        .city("HaNoi")
        .build());
    }
}
