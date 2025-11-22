package pl.kamil.artsiteandstoreapi.application.usecase;

import org.springframework.stereotype.Service;
import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.services.ImageService;
import pl.kamil.artsiteandstoreapi.domain.services.ImageMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FetchMainPageImageListUC {
    private final ImageService fi;
    private final int MAIN_PAGE_ID = 1;

    public FetchMainPageImageListUC(ImageService fi) {
        this.fi = fi;
    }

    public List<ImageDTO> fetch() {
        List<UUID> placeIds = fi.findAllPlaceIdByPageId(MAIN_PAGE_ID);

        List<Image> images = new ArrayList<>();
        for (var placeId : placeIds){
            images.addAll(fi.findAllByPlaceId(placeId));
        }

        return images.stream()
                .map(ImageMapper::toImageDTO)
                .toList();
    }
}
