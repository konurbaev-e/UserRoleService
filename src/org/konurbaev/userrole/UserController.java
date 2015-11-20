package org.konurbaev.userrole;

import org.konurbaev.userrole.model.Role;
import org.konurbaev.userrole.model.RoleRepository;
import org.konurbaev.userrole.model.User;
import org.konurbaev.userrole.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController // http://localhost:8080/user/konurbaev-e
@RequestMapping("user")
public class UserController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("{username}")
    public User readUserByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) throw new UserNotFoundException(username);

        //debug
        System.out.println("REST Get: ");
        System.out.println(user.get().getUsername());
        user.get().getUserrole().forEach(a->System.out.println(a.getRole_id() + " - " + a.getRolename()));
        //end debug

        return user.get();
    }

    @RequestMapping(value="{username}/role", method=RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addRoleToUser(@PathVariable String username, @Valid @RequestBody Role role){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        //Role role1 = roleRepository.findByRolename(role.getRolename()).orElseThrow(() -> new UserNotFoundException(username));
        //List<Role> roleList = new ArrayList<>();
        //roleList.add(role1);
        //user.setUserrole(roleList);

        //debug
        System.out.println("After findByUsername: ");
        System.out.println(user.getUsername());
        user.getUserrole().forEach(a->System.out.println(a.getRole_id() + " - " + a.getRolename()));
        //end debug

        user.addRole(role);

        //debug
        System.out.println("After addRole: ");
        System.out.println(user.getUsername());
        user.getUserrole().forEach(a->System.out.println(a.getRole_id() + " - " + a.getRolename()));
        //end debug

        userRepository.save(user);

        //debug
        user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        System.out.println("After save: ");
        System.out.println(user.getUsername());
        user.getUserrole().forEach(a->System.out.println(a.getRole_id() + " - " + a.getRolename()));
        //end debug

    }

}