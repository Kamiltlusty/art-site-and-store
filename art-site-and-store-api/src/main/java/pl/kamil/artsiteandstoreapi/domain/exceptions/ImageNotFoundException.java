package pl.kamil.artsiteandstoreapi.domain.exceptions;

import java.util.UUID;

public class ImageNotFoundException extends RuntimeException {
    private final UUID uuid;

    public ImageNotFoundException(UUID imageId) {
        super("Image with id " + imageId + " not found");
        this.uuid = imageId;
    }

    public UUID getUUID() {
        return uuid;
    }
}
