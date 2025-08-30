package pl.kamil.artsiteandstoreapi.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;
import pl.kamil.artsiteandstoreapi.infrastracture.PlaceRepository;

import java.sql.SQLOutput;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
  private final PlaceRepository placeRepository;

  public Place findByName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name argument cannot be null");
    }
    String lowerCaseName = name.toLowerCase();

    return placeRepository.findByName(lowerCaseName)
      .orElseThrow(() -> new EntityNotFoundException(lowerCaseName));
  }
}
