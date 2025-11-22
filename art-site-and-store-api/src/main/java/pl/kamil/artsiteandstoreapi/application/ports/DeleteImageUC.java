package pl.kamil.artsiteandstoreapi.application.ports;

import java.util.UUID;

public interface DeleteImageUC {
    void delete(UUID id);
}
