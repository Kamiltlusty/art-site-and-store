package pl.kamil.artsiteandstoreapi.domain.dtos;

public record ImageDTO(String filename, String url, String mime_type, Long size, String description, Integer width,
                       Integer height) {
}
