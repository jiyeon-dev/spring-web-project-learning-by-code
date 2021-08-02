package org.zerock.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/sample/*")
public class SampleController {

    // 6-2
    @RequestMapping(value="/basic", method = { RequestMethod.GET })
    public void basicGet() {
        System.out.println("basic Get.....");
    }

    // 6-3
    // public String ex02(SampleDTO dto) {
    // 6-3-1
    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {

        System.out.println("name: " + name);
        System.out.println("age: " + age);
        return "ex02";

    }
    // 6-3-3
    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list) {

        System.out.println("list dtos: " + list);
        return "ex02Bean";

    }

    // 6-3-4
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//    }
    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {

        System.out.println("todo: " + todo);
        return "ex03";

    }

    // 6-4-1
    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        // http://localhost:8081/sample/ex04?name=aaaa&age=11&page=9
        return "/sample/ex04";

    }
}
