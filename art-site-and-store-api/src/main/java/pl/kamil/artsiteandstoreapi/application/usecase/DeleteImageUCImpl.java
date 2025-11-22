package pl.kamil.artsiteandstoreapi.application.usecase;

import org.springframework.stereotype.Service;
import pl.kamil.artsiteandstoreapi.application.ports.DeleteImageUC;
import pl.kamil.artsiteandstoreapi.domain.services.ImageService;

import java.util.UUID;

@Service
public class DeleteImageUCImpl implements DeleteImageUC {
    private final ImageService is;

    public DeleteImageUCImpl(ImageService is) {
        this.is = is;
    }

    @Override
    public void delete(UUID id) {
        is.delete(id);
    }
}
