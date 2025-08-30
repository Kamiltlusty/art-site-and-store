package pl.kamil.artsiteandstoreapi.domain.dtos;

import lombok.Builder;
import lombok.With;

import java.util.List;

@With
@Builder
public record PlaceDTO(String name, List<ImageDTO> images) {}
