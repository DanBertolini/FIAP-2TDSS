package br.com.natura.fiap.naturatododia.entity;

public class User {
    private int id;
    private String name;
    private String initials;

    public User(){

    }
    public User(int id, String name, String initials){
        this.id = id;
        this.initials = initials;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}
