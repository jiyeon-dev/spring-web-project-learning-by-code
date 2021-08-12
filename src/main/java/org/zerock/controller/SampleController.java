package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/*")
public class SampleController {

    private static final Logger log = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/all")
    public void doAll() {
        log.info("do all ca access everybody");
    }

    @GetMapping("/member")
    public void doMember() {
        log.info("logined member");
    }

    @GetMapping("/admin")
    public void doAdmin() {
        log.info("admin only");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
    @GetMapping("/annoMember")
    public void doMember2() {
        log.info("logined annotation member");
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/annoAdmin")
    public void doAdmin2() {
        log.info("admin annotation only");
    }
}
