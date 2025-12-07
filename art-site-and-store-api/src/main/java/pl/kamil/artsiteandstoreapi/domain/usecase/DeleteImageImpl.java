package pl.kamil.artsiteandstoreapi.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.exceptions.ImageNotFoundException;
import pl.kamil.artsiteandstoreapi.domain.ports.incoming.DeleteImage;
import pl.kamil.artsiteandstoreapi.domain.ports.outgoing.ImageRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteImageImpl implements DeleteImage {
    private final ImageRepository ir;

    @Override
    public void delete(UUID id) {
        Optional<Image> found = ir.findById(id);
        if (found.isEmpty()) {
            throw new ImageNotFoundException(id);
        }
        ir.deleteById(id);
    }
}
