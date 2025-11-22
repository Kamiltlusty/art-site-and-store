package pl.kamil.artsiteandstoreapi.application.dtos;

import lombok.Builder;
import lombok.With;

import java.util.List;

@With
@Builder
public record PlaceDTO(String name, List<ImageDTO> images) {}
