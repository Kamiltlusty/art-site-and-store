package pl.kamil.artsiteandstoreapi.domain.services;

import org.springframework.stereotype.Service;
import pl.kamil.artsiteandstoreapi.application.port.ImageRepository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;

import java.util.List;
import java.util.UUID;

@Service
public class FetchImage {
    private final ImageRepository ir;

    public FetchImage(ImageRepository ir) {
        this.ir = ir;
    }

    public List<Image> findAllByPlaceId(UUID placeId) {
        var listImg = ir.findAllByPlaceId(placeId);
        if (listImg.isEmpty()) {
            throw new RuntimeException("No image found");
        }
        return listImg;
    }

    public List<UUID> findAllPlaceIdByPageId() {
        return null;
    }
}
