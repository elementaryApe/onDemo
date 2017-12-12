package com.udemo.entity;

import java.io.Serializable;

/**
 * Desc: 账户实体
 * User: hansh
 * Date: 2017/12/12
 * Time: 10:52
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -7743866217925673362L;

    private int id ;

    private String name ;

    private double money;

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'money'.
     *
     * @return Value for property 'money'.
     */
    public double getMoney() {
        return money;
    }

    /**
     * Setter for property 'money'.
     *
     * @param money Value to set for property 'money'.
     */
    public void setMoney(double money) {
        this.money = money;
    }
}
