package io.github.javaasasecondlanguage.flitter.services;

import io.github.javaasasecondlanguage.flitter.controllers.dto.FlitAddDTO;
import io.github.javaasasecondlanguage.flitter.entities.Flit;

import java.util.List;

public interface FlitService {

    Flit addFlit(Flit flit);

    List<Flit> getLastFlits(int count);

    List<Flit> getFlitsByUser(String userName);

    List<Flit> getFeedForUser(String userToken);
}
