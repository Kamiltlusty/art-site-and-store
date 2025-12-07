package pl.kamil.artsiteandstoreapi.domain.ports.incoming;

import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;

public interface SaveImage {
    Image save(ImageDTO image);
}
