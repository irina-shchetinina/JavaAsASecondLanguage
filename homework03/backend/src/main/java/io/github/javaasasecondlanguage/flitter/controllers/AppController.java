package io.github.javaasasecondlanguage.flitter.controllers;

import io.github.javaasasecondlanguage.flitter.Storage;
import io.github.javaasasecondlanguage.flitter.controllers.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping("")
public class AppController {

    /**
     * curl -X DELETE 'localhost:8080/clear'
     */
    @RequestMapping(
            path = "clear",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDTO> clear() {
        Storage.clear();
        return ResponseEntity.ok(new ResponseDTO<>());
    }
}
