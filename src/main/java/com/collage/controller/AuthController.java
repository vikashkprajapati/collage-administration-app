package com.collage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("auth")
@Controller
public class AuthController {

    @GetMapping({"login","login/"})
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        request.getSession().invalidate();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getDomain());
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");
            cookie.setHttpOnly(false);
            response.addCookie(cookie);

        }
        return "redirect:/auth/login";
    }
}
