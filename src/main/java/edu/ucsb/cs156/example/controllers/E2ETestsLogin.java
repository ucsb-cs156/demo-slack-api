package edu.ucsb.cs156.example.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Profile("e2etests")
@Controller
public class E2ETestsLogin {

  @RequestMapping("/login")
  public String login() {
    return "login.html";
  }

}
