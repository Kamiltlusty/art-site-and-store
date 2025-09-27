package pl.kamil.artsiteandstoreapi.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamil.artsiteandstoreapi.application.exceptions.ImageNotFoundException;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;
import pl.kamil.artsiteandstoreapi.infrastracture.ImageRepository;
import pl.kamil.artsiteandstoreapi.infrastracture.PlaceRepository;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    private final PlaceRepository placeRepository;
    private final ImageRepository imageRepository;
    private final Logger logger = LoggerFactory.getLogger(ImageService.class);

    public Place findByName(String name) {
        if (name == null) {
            throw new NullPointerException("name argument cannot be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("name argument cannot be empty");
        }
        String lowerCaseName = name.toLowerCase();

        return placeRepository.findByName(lowerCaseName)
                .orElseThrow(() -> new EntityNotFoundException(lowerCaseName));
    }

    public InputStreamResource findByImageId(UUID uuid) throws IOException {
        System.out.println(uuid);
        Image img = imageRepository.findByImageId(uuid)
                .orElseThrow(EntityNotFoundException::new);

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:static/" + img.getFileName());

        return new InputStreamResource(resource.getInputStream());
    }

    public void deleteByImageId(UUID uuid) {
        if (uuid == null) {
            throw new NullPointerException("ImageId cannot be null");
        }

        try {
            imageRepository.deleteByImageId(uuid);
            logger.debug("Successfully deleted image with id {}", uuid);

        } catch (EntityNotFoundException e) {
            logger.debug("Image with id {} not found", uuid);
            throw new ImageNotFoundException(uuid);
        }
    }
}
