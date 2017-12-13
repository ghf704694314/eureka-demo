package com.gaohf.entity;

/**
 * com.gaohf.entity
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/13
 */
public class User {

    private String name;
    private Integer age;

    public User(){}

    public User(String name,Integer age){
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "name="+name+",age="+age;
    }
}
