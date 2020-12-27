package Thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    // greeting 페이지로 name값 넘기기
    // localhost:8080/greeting으로 접속
    // name에 값을 넣을 때,  localhost:8080/greeting?name=Hong 으로 하면 World가 Hong 변경
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
