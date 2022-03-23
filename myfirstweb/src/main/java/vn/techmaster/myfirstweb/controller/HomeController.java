package vn.techmaster.myfirstweb.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.myfirstweb.model.BMI;
import vn.techmaster.myfirstweb.model.Student;
import vn.techmaster.myfirstweb.service.Util;

@Controller
@RequestMapping("/")
public class HomeController {
     /* bài 1 */
  @RequestMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String Random() {
    // return RandomStringUtils.randomAlphanumeric(8);
    return Util.generateId(8);
    
  }

  /* bài 2 */
  @GetMapping(value = "/quote", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String getQuote(){
    String[] x = {"Kiến tha lâu đầy tổ", "Có công mài sắt, có ngày nên kim", "Không thầy đố mày làm nên", "Học thày không tày học bạn"};
    double y = Math.random() * 3;
    return x[(int) y]; 

  }

  /* bài 3 */
  @PostMapping(value = "/bmi", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public double countBMI(@RequestBody BMI bmi) {
    return bmi.getWeight()/(bmi.getHeight()*bmi.getHeight());
  }

  /* bài 4 */
  ArrayList<Student> listOfStudents = new ArrayList<>();
  @GetMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ArrayList<Student> listStudent(){
    return listOfStudents;

  }

  @PostMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Student addStudents(@RequestBody Student student) {
        listOfStudents.add(student);
        return student;
  }
}
