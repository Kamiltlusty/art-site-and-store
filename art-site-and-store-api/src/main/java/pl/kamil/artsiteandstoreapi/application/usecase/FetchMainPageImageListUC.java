package pl.kamil.artsiteandstoreapi.application.usecase;

import org.springframework.stereotype.Service;
import pl.kamil.artsiteandstoreapi.domain.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.services.FetchImage;
import pl.kamil.artsiteandstoreapi.domain.services.ImageMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FetchMainPageImageListUC {
    private final FetchImage fi;

    public FetchMainPageImageListUC(FetchImage fi) {
        this.fi = fi;
    }

    public List<ImageDTO> fetch(List<UUID> placeIds) {
        List<Image> images = new ArrayList<>();
        for (var placeId : placeIds){
            images.addAll(fi.findAllByPlaceId(placeId));
        }

        return images.stream()
                .map(ImageMapper::toImageDTO)
                .toList();
    }
}
