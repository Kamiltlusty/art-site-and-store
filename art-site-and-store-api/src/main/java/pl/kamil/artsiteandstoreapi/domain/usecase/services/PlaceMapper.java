package pl.kamil.artsiteandstoreapi.domain.usecase.services;

import org.springframework.stereotype.Component;
import pl.kamil.artsiteandstoreapi.application.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

@Component
public class PlaceMapper {
    public static PlaceDTO toPlaceDTO(Place place) {
        return PlaceDTO.builder()
                .placeId(place.getPlaceId())
                .name(place.getName())
                .build();
    }
}
