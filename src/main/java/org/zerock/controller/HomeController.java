package org.zerock.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class HomeController {

    @GetMapping("/")
    public void index() {
    }

}
