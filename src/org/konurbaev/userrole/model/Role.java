package org.konurbaev.userrole.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long role_id;

    @Column(unique=true)
    public String rolename;

    @ManyToMany(mappedBy = "userrole")
//    @JsonBackReference
    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@roleId")
    private List<User> userrole;

    public Role (){
    }

    public Role (String rolename){
        this.rolename = rolename;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUserrole() {
        return userrole;
    }

    public void setUserrole(List<User> userrole) {
        this.userrole = userrole;
    }
}
