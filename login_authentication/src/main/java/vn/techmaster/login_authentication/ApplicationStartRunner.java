package vn.techmaster.login_authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import vn.techmaster.login_authentication.service.UserService;

@Component
public class ApplicationStartRunner implements ApplicationRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.addUserThenAutoActivate("Thai", "thaithedoublecheese1@gmail.com", "123456");
    }
}
