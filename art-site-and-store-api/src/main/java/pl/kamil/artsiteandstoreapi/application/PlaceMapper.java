package pl.kamil.artsiteandstoreapi.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kamil.artsiteandstoreapi.domain.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

@Component
@RequiredArgsConstructor
public class PlaceMapper {
  private final ImageMapper imageMapper;

  public PlaceDTO toPlaceDTO(Place place) {
    return PlaceDTO.builder()
      .name(place.getName())
      .images(place.getImages().stream()
        .map(imageMapper::toImageDTO)
        .toList()
      ).build();
  }
}
