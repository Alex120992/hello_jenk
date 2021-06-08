package ru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


/**
 * @author Zateev Aleksey
 */

@Controller
public class HomeController {


    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("personData", new PersonData());
        return "index";
    }

    @PostMapping("/sendmail")
    public String sendMail(@Valid @ModelAttribute("personData") PersonData person,
                           BindingResult bindingResult,
                           @Autowired MailSender mailSender
    ) {
        if (bindingResult.hasErrors()) {
            return "#";
        } else {
            try {
               mailSender.sentMessage(person.getMessage(),person.getMail());
            } catch (MailException e) {
                e.getMessage();
            }
            return "greeting-message";
        }
    }
}
