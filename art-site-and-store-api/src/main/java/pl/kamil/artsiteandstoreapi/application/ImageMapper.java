package pl.kamil.artsiteandstoreapi.application;

import org.springframework.stereotype.Component;
import pl.kamil.artsiteandstoreapi.domain.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;

@Component
public class ImageMapper {
  public ImageDTO toImageDTO(Image image) {
    return ImageDTO.builder()
      .imageId(image.getImageId())
      .fileName(image.getFileName())
      .url(image.getUrl())
      .mimeType(image.getMimeType())
      .size(image.getSize())
      .description(image.getDescription())
      .height(image.getHeight())
      .width(image.getWidth())
      .build();
  }
}
