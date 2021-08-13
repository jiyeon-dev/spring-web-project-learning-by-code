package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService service;

//    @GetMapping("/list")
//    public void list(Model model) {
//
//        log.info("list");
//        model.addAttribute("list", service.getList());
//
//    }
    @GetMapping("/list")
    public void list(Criteria cri, Model model) {

        log.info("list: " + cri);
        model.addAttribute("list", service.getList(cri));

        int total = service.getTotal(cri);
        log.info("total: " + total);
        model.addAttribute("pageMaker", new PageDTO(cri, total));

    }

    @GetMapping("/register")
    @PreAuthorize("isAuthenticated()")
    public void register() {
        log.info("==============================================register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAuthenticated()")
    public String register(BoardVO board, RedirectAttributes rttr) {

        log.info("============================================");
        log.info("register: " + board);

        if (board.getAttachList() != null) {
            board.getAttachList().forEach(attach -> log.info("" + attach));
        }
        log.info("============================================");

        service.register(board);

        rttr.addFlashAttribute("result", board.getNo());

        return "redirect:/board/list";

    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("no") Long no, @ModelAttribute("cri") Criteria cri, Model model) {

        log.info("/get or /modify");
        model.addAttribute("board", service.get(no));

    }

    @PostMapping("/modify")
    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {

        log.info("modify: " + board);

        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }

        return "redirect:/board/list" + cri.getListLink();

    }

    /**
     * 폴더 내 첨부파일(일반파일, 썸네일) 삭제
     * @param attachList
     */
    private void deleteFile(List<BoardAttachVO> attachList) {
        if (attachList == null || attachList.size() == 0) {
            return;
        }

        log.info("delete attach files...............................");

        attachList.forEach(attach -> {
            try {
                Path file = Paths.get("C:\\upload\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());
                Files.deleteIfExists(file);

                // 이미지인 경우 썸네일 삭제
                if (Files.probeContentType(file).startsWith("image")) {
                    Path thumbnail = Paths.get("C:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_" + attach.getFileName());
                    Files.delete(thumbnail);
                }
            } catch (Exception e) {
                log.error("delete file error" + e.getMessage());
            }
        });

    }

    @PostMapping("/remove")
    public String remove(@RequestParam("no") Long no, @ModelAttribute Criteria cri, RedirectAttributes rttr) {

        log.info("remove ... " + no);

        List<BoardAttachVO> attachList = service.getAttachList(no);  // 게시물 첨부파일 리스트


        if (service.remove(no)) {  // 데이터베이스에서 데이터 삭제
            deleteFile(attachList);  // 폴더 내 이미지, 파일 삭제
            rttr.addFlashAttribute("result", "success");
        }

        return "redirect:/board/list" + cri.getListLink();

    }

    @GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<BoardAttachVO>> getAttachList(Long no) {

        log.info("getAttachList " + no);
        return new ResponseEntity<List<BoardAttachVO>>(service.getAttachList(no), HttpStatus.OK);

    }

}
