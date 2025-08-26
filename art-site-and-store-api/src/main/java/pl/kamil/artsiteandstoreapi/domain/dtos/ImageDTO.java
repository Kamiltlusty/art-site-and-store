package pl.kamil.artsiteandstoreapi.domain.dtos;

import java.util.UUID;

public record ImageDTO(UUID imageId, String fileName, String url, String mimeType, Long size, String description, Integer width,
                       Integer height) {
}
