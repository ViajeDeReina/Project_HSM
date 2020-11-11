package com.vitec.vhsm.domain;


import javax.persistence.*;

@Entity
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "can_use")
    private Boolean use;

    @Column(name = "registrant")
    private String registrant;

    @Column(name = "register_date")
    private String register_date;

    public Admin() {}

    public Admin(String user_id, String password, String name, String department, String mobile, String email, Boolean use, String registrant, String register_date) {
        this.user_id = user_id;
        this.password = password;
        this.name = name;
        this.department = department;
        this.mobile = mobile;
        this.email = email;
        this.use = use;
        this.registrant = registrant;
        this.register_date  = register_date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getUse() { return use; }

    public void setUse(Boolean use) {
        this.use = use;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }
}
