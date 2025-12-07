package pl.kamil.artsiteandstoreapi.application.dtos;

import lombok.Builder;
import lombok.With;

import java.util.UUID;

@With
@Builder
public record PlaceDTO(UUID placeId, String name) {}
