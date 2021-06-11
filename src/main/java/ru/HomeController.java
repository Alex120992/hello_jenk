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
import java.sql.*;
import java.util.Properties;


/**
 * @author Zateev Aleksey
 */

@Controller
public class HomeController {


    @RequestMapping("/")
    public String homePage(Model model) {
        String a;
        PersonData personData;

        String url = "jdbc:postgresql://localhost/alex";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","1234");
        props.setProperty("ssl","true");
        try( Connection conn = DriverManager.getConnection(url, props)) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM andersen");
            a = resultSet.getString(1);
            personData = new PersonData();
            personData.setName(a);
            model.addAttribute("personData", personData);
        } catch (SQLException e) {
            e.printStackTrace();
        }



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
