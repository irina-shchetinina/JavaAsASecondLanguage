package io.github.javaasasecondlanguage.flitter.controllers;

import io.github.javaasasecondlanguage.flitter.controllers.dto.FlitDiscoverDTO;
import io.github.javaasasecondlanguage.flitter.controllers.dto.ResponseDTO;
import io.github.javaasasecondlanguage.flitter.controllers.dto.SubscribeDTO;
import io.github.javaasasecondlanguage.flitter.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriptionController {
    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * curl -X POST 'localhost:8080/subscribe' -d '{"subscriberToken": "7b74505e-e07a-4544-b060-909956d2161c", "publisherName": "sergey"}' -H "Content-Type: application/json"
     */
    @RequestMapping(
            path = "subscribe",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDTO> subscribe(@RequestBody SubscribeDTO subscribeDTO) {

        subscriptionService.subscribe(subscribeDTO.getSubscriberToken(),
                subscribeDTO.getPublisherName());
        return ResponseEntity.ok(new ResponseDTO<>());
    }

    /**
     * curl -X POST 'localhost:8080/unsubscribe' -d '{"subscriberToken": "7b74505e-e07a-4544-b060-909956d2161c", "publisherName": "sergey"}' -H "Content-Type: application/json"
     */
    @RequestMapping(
            path = "unsubscribe",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDTO> unsubscribe(@RequestBody SubscribeDTO subscribeDTO) {

        subscriptionService.unsubscribe(subscribeDTO.getSubscriberToken(),
                subscribeDTO.getPublisherName());
        return ResponseEntity.ok(new ResponseDTO<>());
    }

    /**
     * curl 'localhost:8080/subscribers/list/7b74505e-e07a-4544-b060-909956d2161c'
     */
    @RequestMapping(
            path = "subscribers/list/{usertoken}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<String>>> subscribersList(@PathVariable String usertoken) {
        var userNames = subscriptionService.getSubscribersNames(usertoken);
        var responseBody = new ResponseDTO<>(userNames);
        return ResponseEntity.ok(responseBody);
    }

    /**
     * curl 'localhost:8080/publishers/list/7b74505e-e07a-4544-b060-909956d2161c'
     */
    @RequestMapping(
            path = "publishers/list/{usertoken}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<String>>> publishersList(@PathVariable String usertoken) {
        var userNames = subscriptionService.getPublishersNames(usertoken);
        var responseBody = new ResponseDTO<>(userNames);
        return ResponseEntity.ok(responseBody);
    }
}
