package org.example.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class User {
    String id;

    LocalDateTime date;

    String login;

    String password;

    String confirmPassword;

    String lastName;

    String name;

    String middleName;

    Integer age;

    Boolean isWorker;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getWorker() {
        return isWorker;
    }

    public void setWorker(Boolean worker) {
        isWorker = worker;
    }

    public void checkFields() {
        if (login.length() > 20) {
            throw new RuntimeException("Длинна логина больше 20 символов");
        }

        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Пароли не совпадают");
        }

        if (password.length() > 20) {
            throw new RuntimeException("Длинна пароля больше 20 символов");
        }
    }

    public String toString() {
        return id + "|" + date.format((new DateTimeFormatterBuilder()).append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).appendLiteral('T').append(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")).toFormatter()) + "|" + login + "|" + password +
                "|" + confirmPassword + "|" + lastName + "|" + name +
                "|" + (middleName == null ? "" : middleName) +
                "|" + (age == null ? "" : age) + "|" + isWorker;
    }


    public User() {

    }
    public User(String id, LocalDateTime date, String login, String password, String confirmPassword, String lastName, String name, String middleName, Integer age, Boolean isWorker) {
        this.id = id;
        this.date = date;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.lastName = lastName;
        this.name = name;
        this.middleName = middleName;
        this.age = age;
        this.isWorker = isWorker;
    }
}
