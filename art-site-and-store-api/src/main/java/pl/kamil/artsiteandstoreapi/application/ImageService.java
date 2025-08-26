package pl.kamil.artsiteandstoreapi.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamil.artsiteandstoreapi.domain.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ImageService {
  public List<Place> findAll() {

    return List.of();
  }
}
