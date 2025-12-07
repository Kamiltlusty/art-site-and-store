package pl.kamil.artsiteandstoreapi.domain.usecase.services;

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

    public Image toImage(ImageDTO imageDTO) {
        return Image.builder()
                .imageId(imageDTO.imageId())
                .fileName(imageDTO.fileName())
                .path(imageDTO.path())
                .mimeType(imageDTO.mimeType())
                .size(imageDTO.size())
                .description(imageDTO.description())
                .width(imageDTO.width())
                .height(imageDTO.height())
                .build();
    }
}
