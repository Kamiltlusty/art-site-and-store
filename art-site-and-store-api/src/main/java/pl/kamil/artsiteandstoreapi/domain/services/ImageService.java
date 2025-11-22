package pl.kamil.artsiteandstoreapi.domain.services;

import org.springframework.stereotype.Service;
import pl.kamil.artsiteandstoreapi.application.exceptions.ImageNotFoundException;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.ports.ImageRepository;
import pl.kamil.artsiteandstoreapi.domain.ports.PlaceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {
    private final ImageRepository ir;
    private final PlaceRepository pr;

    public ImageService(ImageRepository ir, PlaceRepository pr) {
        this.ir = ir;
        this.pr = pr;
    }

    public List<Image> findAllByPlaceId(UUID placeId) {
        var listImg = ir.findAllByPlace_PlaceId(placeId);
        if (listImg.isEmpty()) {
            throw new RuntimeException("No image found");
        }
        return listImg;
    }

    public List<UUID> findAllPlaceIdByPageId(Integer pageId) {
        return pr.findAllPlaceIdByPage_PageId(pageId);
    }

    public void delete(UUID id) {
        Optional<Image> found = ir.findById(id);
        if (found.isEmpty()) {
            throw new ImageNotFoundException(id);
        }
        ir.deleteById(id);
    }
}
