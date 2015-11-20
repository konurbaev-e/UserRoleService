package org.konurbaev.userrole.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long user_id;

    @Column(unique=true)
    private String username;

    @ManyToMany
    @JoinTable(name = "userrole", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    @JsonManagedReference
    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@userId")
    private List<Role> userrole = new ArrayList<>();

    public User (){
    }

    public User (String username){
        this.username = username;
    }

    public User (String username, List<Role> userrole){
        this.username = username;
        this.userrole = userrole;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Role> getUserrole() {
        return userrole;
    }

    public void setUserrole(List<Role> userrole) {
        this.userrole = userrole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addRole (Role role) {
        userrole.add(role);
    }

}
