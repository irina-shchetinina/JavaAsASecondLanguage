package io.github.javaasasecondlanguage.flitter.controllers;

import io.github.javaasasecondlanguage.flitter.controllers.dto.NewUserDTO;
import io.github.javaasasecondlanguage.flitter.controllers.dto.ResponseDTO;
import io.github.javaasasecondlanguage.flitter.entities.User;
import io.github.javaasasecondlanguage.flitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * curl -X POST 'localhost:8080/user/register' -d '{"userName": "sasha"}' -H "Content-Type: application/json"
     */
    @RequestMapping(
            path = "register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDTO<User>> register(@RequestBody NewUserDTO userDTO) {
        var user = userService.registerUser(userDTO.getUserName());
        var responseBody = new ResponseDTO<>(user);
        return ResponseEntity.ok(responseBody);
    }

    /**
     * curl 'localhost:8080/user/list'
     */
    @RequestMapping(
            path = "list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<String>>> list() {
        var userList = userService.getUserNamesList();
        var responseBody = new ResponseDTO<>(userList);
        return ResponseEntity.ok(responseBody);
    }
}
