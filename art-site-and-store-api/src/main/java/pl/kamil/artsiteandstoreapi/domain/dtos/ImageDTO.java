package pl.kamil.artsiteandstoreapi.domain.dtos;

import lombok.Builder;
import lombok.With;

import java.util.UUID;

@With
@Builder
public record ImageDTO(UUID imageId, String fileName, String path, String mimeType, Long size, String description, Integer width, Integer height) {}
