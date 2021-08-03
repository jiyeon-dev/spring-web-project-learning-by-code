package org.zerock.controller;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    private static final Logger log = Logger.getLogger(BoardController.class);

    @Autowired
    private BoardService service;

    @GetMapping("/list")
    public void list(Model model) {

        log.info("list");
        model.addAttribute("list", service.getList());

    }

}
