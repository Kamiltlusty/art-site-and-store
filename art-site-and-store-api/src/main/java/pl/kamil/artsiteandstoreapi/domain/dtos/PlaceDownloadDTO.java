package pl.kamil.artsiteandstoreapi.domain.dtos;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record PlaceDownloadDTO(String name, ImageDTO image) {}
