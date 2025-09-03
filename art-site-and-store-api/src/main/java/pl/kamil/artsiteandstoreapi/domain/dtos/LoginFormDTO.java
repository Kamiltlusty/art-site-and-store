package pl.kamil.artsiteandstoreapi.domain.dtos;

import lombok.Value;


public record LoginFormDTO(String username, String password) {
}
