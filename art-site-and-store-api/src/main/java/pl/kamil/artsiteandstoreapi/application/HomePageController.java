package pl.kamil.artsiteandstoreapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kamil.artsiteandstoreapi.domain.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

import java.util.List;
import java.util.UUID;


@Slf4j
@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class HomePageController {
  private final ImageService imageService;
  private final ImageMapper imageMapper;
  private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);

  @GetMapping("/carousel")
  public ResponseEntity<List<ImageDTO>> getCarousel() {
    List<ImageDTO> carousel = imageService.findByName("carousel").getImages().stream()
      .map(imageMapper::toImageDTO).toList();

    return ResponseEntity
      .ok()
      .body(carousel);
  }

//  @GetMapping("/carousel/{uuid}")
//  public ResponseEntity<InputStreamResource> getCarousel(@PathVariable UUID uuid) {
//
//  }

}
