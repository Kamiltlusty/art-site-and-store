package pl.kamil.artsiteandstoreapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kamil.artsiteandstoreapi.domain.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class HomePageController {
  private final ImageService imageService;
  private final ImageMapper imageMapper;
  private final PlaceMapper placeMapper;
  private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);

  @GetMapping("/carousel")
  public ResponseEntity<List<PlaceDTO>> getCarousel() {
    List<Place> places = new ArrayList<>();
    Place carousel = imageService.findByName("carousel");
    Place aboutMe = imageService.findByName("about_me");
    Place portfolio = imageService.findByName("portfolio");

    places.add(carousel);
    places.add(aboutMe);
    places.add(portfolio);

    List<PlaceDTO> home = places.stream()
      .map(placeMapper::toPlaceDTO)
      .toList();

    System.out.println(home);

    return ResponseEntity
      .ok()
      .body(home);
  }

  @GetMapping("/carousel/{uuid}")
  public ResponseEntity<InputStreamResource> getCarousel(@PathVariable UUID uuid) throws IOException {
    InputStreamResource isr = imageService.findByImageId(uuid);
    HttpHeaders headers = new HttpHeaders();

    headers.add("Content-type", "image/jpeg");

    return ResponseEntity
      .ok()
      .headers(headers)
      .body(isr);
  }

}
