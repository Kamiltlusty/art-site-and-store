package pl.kamil.artsiteandstoreapi.domain.exceptions;

public class ImageSizeExceeded extends RuntimeException {
    public ImageSizeExceeded(Long size) {
        super("Image size exceeded: " + size);
    }
}
