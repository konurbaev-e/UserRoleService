package org.konurbaev.userrole;

import org.konurbaev.userrole.model.Role;
import org.konurbaev.userrole.model.User;
import org.konurbaev.userrole.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController // http://localhost:8080/user/konurbaev-e
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //get main attributes of user
    @RequestMapping("{username}")
    public User readUserByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) throw new UserNotFoundException(username);
        return user.get();
    }

    //to add only 1 role to user
    @RequestMapping(value="{username}/role", method=RequestMethod.POST)
    public ResponseEntity addRoleToUser(@PathVariable String username, @Valid @RequestBody Role role){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        user.addRole(role);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //get roles of definite user
    @RequestMapping(value="{username}/roles", method=RequestMethod.GET)
    public Collection<Role> getUserRoles (@PathVariable String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return user.getUserrole();
    }

    //to add several roles to user
    @RequestMapping(value="{username}/roles", method=RequestMethod.POST)
    public ResponseEntity addRolesToUser(@PathVariable String username, @Valid @RequestBody List<Role> roleList){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        user.addRoles(roleList);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}