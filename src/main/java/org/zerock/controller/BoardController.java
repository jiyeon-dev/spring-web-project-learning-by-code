package org.zerock.controller;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
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

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {

        log.info("register: " + board);

        service.register(board);

        rttr.addFlashAttribute("result", board.getNo());

        return "redirect:/board/list";

    }

    @GetMapping("/get")
    public void get(@RequestParam("no") Long no, Model model) {

        log.info("/get");
        model.addAttribute("board", service.get(no));

    }

    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr) {

        log.info("modify: " + board);

        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }

        return "redirect:/board/list";

    }

    @PostMapping("/remove")
    public String remove(@RequestParam("no") Long no, RedirectAttributes rttr) {

        log.info("remove ... " + no);
        if (service.remove(no)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";

    }

}