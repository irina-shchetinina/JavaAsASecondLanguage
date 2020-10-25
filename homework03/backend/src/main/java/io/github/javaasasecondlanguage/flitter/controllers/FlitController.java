package io.github.javaasasecondlanguage.flitter.controllers;

import io.github.javaasasecondlanguage.flitter.controllers.dto.DTOUtils;
import io.github.javaasasecondlanguage.flitter.controllers.dto.FlitAddDTO;
import io.github.javaasasecondlanguage.flitter.controllers.dto.FlitDiscoverDTO;
import io.github.javaasasecondlanguage.flitter.controllers.dto.ResponseDTO;
import io.github.javaasasecondlanguage.flitter.entities.Flit;
import io.github.javaasasecondlanguage.flitter.services.FlitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flit")
public class FlitController {
    private FlitService flitService;

    @Autowired
    public FlitController(FlitService flitService) {
        this.flitService = flitService;
    }

    /**
     * curl -X POST 'localhost:8080/flit/add' -d '{"userToken": "7b74505e-e07a-4544-b060-909956d2161c", "content": "I like Java!"}' -H "Content-Type: application/json"
     */
    @RequestMapping(
            path = "add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDTO<Flit>> register(@RequestBody FlitAddDTO flitAddDTO) {
        var flit = flitService.addFlit(DTOUtils.getFlitFromDTO(flitAddDTO));
        var responseBody = new ResponseDTO<>(flit);
        return ResponseEntity.ok(responseBody);
    }

    /**
     * curl 'localhost:8080/flit/discover'
     */
    @RequestMapping(
            path = "discover",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<FlitDiscoverDTO>>> discover() {
        var flitList = DTOUtils
                .listFlitsToListFlitsDiscoverDTO(flitService.getLastFlits(10));
        var responseBody = new ResponseDTO<>(flitList);
        return ResponseEntity.ok(responseBody);
    }

    /**
     * curl 'localhost:8080/flit/list/sasha'
     */
    @RequestMapping(
            path = "list/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<FlitDiscoverDTO>>> listByUser(@PathVariable String username) {
        var flitList = DTOUtils
                .listFlitsToListFlitsDiscoverDTO(flitService.getFlitsByUser(username));
        var responseBody = new ResponseDTO<>(flitList);
        return ResponseEntity.ok(responseBody);
    }

    /**
     * curl 'localhost:8080/flit/list/feed/7b74505e-e07a-4544-b060-909956d2161c'
     */
    @RequestMapping(
            path = "list/feed/{usertoken}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<FlitDiscoverDTO>>> feedForUser(@PathVariable String usertoken) {
        var flitList = DTOUtils
                .listFlitsToListFlitsDiscoverDTO(flitService.getFeedForUser(usertoken));
        var responseBody = new ResponseDTO<>(flitList);
        return ResponseEntity.ok(responseBody);
    }
}
