package com.littlehotel.controller;

import com.littlehotel.model.Room;
import com.littlehotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@Transactional
public class ReceptionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "reception", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {

        model.addAttribute("users", userService.getAllUsers());
        return "receptionList";
    }

}
