package ru;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Данный класс является моделью, чьи
 * геттеры и сеттеры связаны с html.jsp
 */
public class PersonData {
    @NotBlank(message = "Поле не может быть пустым")
    private String name;
    @Size(min = 10, message = "количество символов меньше 10")
    private String message;
    @NotBlank(message = "Поле не может быть пустым")
    private String mail;

    public PersonData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMessage() {
        return message;
    }

    public String getMail() {
        return mail;
    }
}
