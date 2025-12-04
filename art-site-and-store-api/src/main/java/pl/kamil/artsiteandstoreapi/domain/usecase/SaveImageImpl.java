package pl.kamil.artsiteandstoreapi.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.ports.incoming.SaveImage;
import pl.kamil.artsiteandstoreapi.domain.ports.outgoing.ImageRepository;
import pl.kamil.artsiteandstoreapi.domain.usecase.services.ImageMapper;
import pl.kamil.artsiteandstoreapi.infrastracture.utils.UUIDGeneratorImpl;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaveImageImpl implements SaveImage {
    private final ImageRepository ir;
    private final ImageMapper im;
    private final UUIDGeneratorImpl uuidGenerator;

    @Override
    public Image save(ImageDTO imageDTO) {
        UUID uuid = uuidGenerator.generateUUID();
        Image image = im.toImage(imageDTO)
                .withImageId(uuid)
                .withPath(uuid.toString());
        return ir.save(image);
    }
}
