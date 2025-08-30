package pl.kamil.artsiteandstoreapi.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

  public Place findByName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name argument cannot be null");
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
}
