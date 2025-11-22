package pl.kamil.artsiteandstoreapi.application.dtos;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record PlaceUploadDTO(String name, ImageUploadDTO imageUploadDTO) {}
