package com.example.haircutscheduling.classes;

public class User
{
    private String name;
    private String email;
    private String password;
    private String phone;

    // TODO:: List bookedAppointment ?
    // TODO:: List History ?

    public User(String name, String email, String password, String phone) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setPhone(phone);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
