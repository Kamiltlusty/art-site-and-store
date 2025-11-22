package pl.kamil.artsiteandstoreapi.domain.services;

import org.springframework.stereotype.Component;
import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;

@Component
public class ImageMapper {
    public static ImageDTO toImageDTO(Image image) {
        return ImageDTO.builder()
                .imageId(image.getImageId())
                .fileName(image.getFileName())
                .path(image.getPath())
                .mimeType(image.getMimeType())
                .size(image.getSize())
                .description(image.getDescription())
                .width(image.getWidth())
                .height(image.getHeight())
                .build();
    }
}
