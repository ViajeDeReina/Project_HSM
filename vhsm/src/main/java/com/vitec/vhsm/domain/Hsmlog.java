package com.vitec.vhsm.domain;

import javax.persistence.*;

@Entity
public class Hsmlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "ip")
    private String ip;

    @Column(name = "mode")
    private String mode;

    @Column(name = "temper")
    private String temper;

    @Column(name = "temper_date")
    private String temper_date;

    @Column(name = "temper_cause")
    private String temper_cause;

    @Column(name = "cpu_usage")
    private String cpu_usage;

    @Column(name = "sys_date")
    private String sys_date;

    @Column(name = "transac")
    private int transac;

    public Hsmlog() {
    }

    public Hsmlog(int id, String type, String ip, String mode, String temper, String temper_date, String temper_cause, String cpu_usage, String sys_date, int transac) {
        this.id = id;
        this.type = type;
        this.ip = ip;
        this.mode = mode;
        this.temper = temper;
        this.temper_date = temper_date;
        this.temper_cause = temper_cause;
        this.cpu_usage = cpu_usage;
        this.sys_date = sys_date;
        this.transac = transac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTemper() {
        return temper;
    }

    public void setTemper(String temper) {
        this.temper = temper;
    }

    public String getTemper_date() {
        return temper_date;
    }

    public void setTemper_date(String temper_date) {
        this.temper_date = temper_date;
    }

    public String getTemper_cause() {
        return temper_cause;
    }

    public void setTemper_cause(String temper_cause) {
        this.temper_cause = temper_cause;
    }

    public String getCpu_usage() {
        return cpu_usage;
    }

    public void setCpu_usage(String cpu_usage) {
        this.cpu_usage = cpu_usage;
    }

    public String getSys_date() {
        return sys_date;
    }

    public void setSys_date(String sys_date) {
        this.sys_date = sys_date;
    }

    public int getTransac() {
        return transac;
    }

    public void setTransac(int transac) {
        this.transac = transac;
    }
}
