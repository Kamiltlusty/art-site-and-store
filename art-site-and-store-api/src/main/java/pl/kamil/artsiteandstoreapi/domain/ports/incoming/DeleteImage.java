package pl.kamil.artsiteandstoreapi.domain.ports.incoming;

import java.util.UUID;

public interface DeleteImage {
    void delete(UUID id);
}
