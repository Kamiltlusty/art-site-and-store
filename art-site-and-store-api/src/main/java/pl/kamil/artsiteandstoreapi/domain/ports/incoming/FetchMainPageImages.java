package pl.kamil.artsiteandstoreapi.domain.ports.incoming;

import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.application.dtos.PlaceDTO;

import java.util.List;
import java.util.Map;

public interface FetchMainPageImages {
    Map<PlaceDTO, List<ImageDTO>> fetch();
}
