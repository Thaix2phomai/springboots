package vn.techmaster.job_hunt.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import vn.techmaster.job_hunt.model.Employer;

@Repository
public class EmployerRepo {
    private ConcurrentHashMap<String, Employer> listEmployers = new ConcurrentHashMap<>();

    public EmployerRepo() {

    }

    // Sinh uuid + add new Employer
    public Employer add(Employer newEmployer) {
        String id = UUID.randomUUID().toString();
        newEmployer.setId(id);
        listEmployers.put(id, newEmployer);
        return newEmployer;
    }

    // Lấy danh sách
    public Collection<Employer> getAll() {
        return listEmployers.values();
    }

    // Cập nhật logo của Employer
    public void updateLogo(String id, String logo_path) {
        var emp = listEmployers.get(id);
        emp.setLogo_path(logo_path);
        listEmployers.put(id, emp);
    }

    public Employer deleteById(String id) {
        // var emp = employers.get(id);
        return listEmployers.remove(id);
    }

    // Lấy Employer theo id
    public Employer findById(String id) {
        return listEmployers.get(id);
    }

    // Thêm dữ liệu
    // Builder nối chuỗi
    // @PostConstruct là một annotation được sử dụng trên một phương thức cần được
    // thực thi sau khi thực hiện chèn phụ thuộc để thực hiện bất kỳ quá trình khởi
    // tạo nào.
    @PostConstruct
    public void addSomeData() {
        this.add(Employer.builder().name("FPT")
                .website("https://fpt.com.vn")
                .email("fpt@gmail.com").logo_path("logo_fpt.png").build());

        this.add(Employer.builder().name("CMC")
                .website("https://cmc.com.vn")
                .email("cmc@gmail.com").logo_path("logo_cmc.png").build());

        this.add(Employer.builder().name("Amazon")
                .website("https://www.amazon.com")
                .email("amazon@gmail.com").logo_path("logo_amazon.png").build());

        this.add(Employer.builder().name("Google")
                .website("https://www.google.com.vn/")
                .email("google@gmail.com").logo_path("logo_google.png").build());
    }

}
