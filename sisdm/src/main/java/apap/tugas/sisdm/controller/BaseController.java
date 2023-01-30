package apap.tugas.sisdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    private String Home() {
        return "home";
    }

//    @GetMapping("/")
//    private String Homee() {
//        return "home";
//    }
}