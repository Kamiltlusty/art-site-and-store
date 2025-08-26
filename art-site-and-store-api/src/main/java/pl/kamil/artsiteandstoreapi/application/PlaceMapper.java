package pl.kamil.artsiteandstoreapi.application;

import org.springframework.stereotype.Component;
import pl.kamil.artsiteandstoreapi.domain.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

@Component
public class PlaceMapper {
  public PlaceDTO toPlaceDTO(Place place) {
    return PlaceDTO.builder().build();
  }
}
