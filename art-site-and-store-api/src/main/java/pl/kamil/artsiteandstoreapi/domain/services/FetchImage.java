package pl.kamil.artsiteandstoreapi.domain.services;

import org.springframework.stereotype.Service;
import pl.kamil.artsiteandstoreapi.application.port.ImageRepository;
import pl.kamil.artsiteandstoreapi.application.port.PlaceRepository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;

import java.util.List;
import java.util.UUID;

@Service
public class FetchImage {
    private final ImageRepository ir;
    private final PlaceRepository pr;

    public FetchImage(ImageRepository ir, PlaceRepository pr) {
        this.ir = ir;
        this.pr = pr;
    }

    public List<Image> findAllByPlaceId(UUID placeId) {
        var listImg = ir.findAllByPlaceId(placeId);
        if (listImg.isEmpty()) {
            throw new RuntimeException("No image found");
        }
        return listImg;
    }


    public List<UUID> findAllPlaceIdByPageId(Integer pageId) {
        return pr.findAllPlaceIdByPageId(pageId);
    }
}
