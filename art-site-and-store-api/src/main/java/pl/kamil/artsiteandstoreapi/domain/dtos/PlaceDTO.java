package pl.kamil.artsiteandstoreapi.domain.dtos;

import lombok.Builder;
import lombok.With;

import java.util.List;

@With
@Builder
public class PlaceDTO {
  String name;
  List<ImageDTO> imagesDTO;
}
