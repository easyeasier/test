package wz;

import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wz._1.model.Person;

import java.util.List;

@SpringBootApplication
@Controller
public class ThymeleafController {

    @RequestMapping("/")
    public String index(Model model) {
        Person singlePerson = new Person(20, "singleW");
        List<Person> people = Lists.newArrayList();
        Person p1 = new Person(21, "p1");
        Person p2 = new Person(22, "p2");
        Person p3 = new Person(23, "p3");

        people.add(p1);
        people.add(p2);
        people.add(p3);

        model.addAttribute("singlePerson", singlePerson);
        model.addAttribute("people", people);
        return "index";
    }


    public static void main(String[] args) {
        SpringApplication.run(ThymeleafController.class, args);
    }
}
