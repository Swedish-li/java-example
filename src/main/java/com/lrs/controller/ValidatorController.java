package com.lrs.controller;

import com.lrs.model.UserVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("valid")
public class ValidatorController {

    @RequestMapping("valid-user")
    public String validUserVo(@Valid @ModelAttribute UserVo user, BindingResult rs) {
        System.out.println(rs);
        return rs.toString();
    }

}
