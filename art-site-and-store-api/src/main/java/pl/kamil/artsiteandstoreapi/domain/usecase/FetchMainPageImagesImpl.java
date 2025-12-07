package pl.kamil.artsiteandstoreapi.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.application.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.ports.incoming.FetchMainPageImages;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.ports.outgoing.ImageRepository;
import pl.kamil.artsiteandstoreapi.domain.ports.outgoing.PlaceRepository;
import pl.kamil.artsiteandstoreapi.domain.usecase.services.ImageMapper;
import pl.kamil.artsiteandstoreapi.domain.usecase.services.PlaceMapper;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FetchMainPageImagesImpl implements FetchMainPageImages {
    private final int MAIN_PAGE_ID = 1;
    private final PlaceRepository pr;
    private final ImageRepository ir;

    @Override
    public Map<PlaceDTO, List<ImageDTO>> fetch() {
        Map<PlaceDTO, List<ImageDTO>> result = new HashMap<>();
        List<PlaceDTO> places = pr.findAllByPage_PageId(MAIN_PAGE_ID).stream()
                .map(PlaceMapper::toPlaceDTO).toList();

        List<ImageDTO> images = new ArrayList<>();
        for (var place : places){
            images.addAll(
                    findAllByPlaceId(place.placeId()).stream()
                            .map(ImageMapper::toImageDTO).toList()
            );
            result.put(place, images);
        }
        return result;
    }

    private List<Image> findAllByPlaceId(UUID placeId) {
        var listImg = ir.findAllByPlace_PlaceId(placeId);
        if (listImg.isEmpty()) {
            throw new RuntimeException("No image found");
        }
        return listImg;
    }
}
