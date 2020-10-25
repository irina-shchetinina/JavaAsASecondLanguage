package io.github.javaasasecondlanguage.flitter.controllers.dto;

import io.github.javaasasecondlanguage.flitter.Storage;
import io.github.javaasasecondlanguage.flitter.entities.Flit;
import io.github.javaasasecondlanguage.flitter.services.exceptions.IncorrectFlitContent;
import io.github.javaasasecondlanguage.flitter.services.exceptions.UserNotFoundByTokenException;

import java.util.List;
import java.util.stream.Collectors;

public class DTOUtils {

    public static Flit getFlitFromDTO(FlitAddDTO flitAddDTO) {
        var user = Storage.getUserByToken(flitAddDTO.getUserToken());
        if (user == null) {
            throw new UserNotFoundByTokenException(flitAddDTO.getUserToken());
        }
        if (flitAddDTO.getContent().isEmpty()) {
            throw new IncorrectFlitContent("Flit content is too short");
        }
        return new Flit(user, flitAddDTO.getContent());
    }

    public static FlitDiscoverDTO getFlitDiscoverDTOFromEntity(Flit flit) {
        return new FlitDiscoverDTO(flit.getUser().getName(), flit.getContent());
    }

    public static List<FlitDiscoverDTO> listFlitsToListFlitsDiscoverDTO(List<Flit> flits) {
        return flits
                .stream()
                .map(DTOUtils::getFlitDiscoverDTOFromEntity)
                .collect(Collectors.toList());
    }
}
