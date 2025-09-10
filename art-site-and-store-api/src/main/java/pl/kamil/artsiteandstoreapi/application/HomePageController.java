package pl.kamil.artsiteandstoreapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kamil.artsiteandstoreapi.domain.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.dtos.PlaceUploadDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequiredArgsConstructor
public class HomePageController {
    private final ImageService imageService;
    private final ImageMapper imageMapper;
    private final PlaceMapper placeMapper;
    private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);

    @PostMapping(value = "/carousel/manage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postImage(
            @RequestPart(name = "place") PlaceUploadDTO placeUploadDTO,
            @RequestPart(name = "image") MultipartFile image) {


        return ResponseEntity.ok().body("image has been uploaded");
    }

    @DeleteMapping("/carousel/manage/{uuid}")
    public ResponseEntity<String> deleteImage(@PathVariable UUID uuid) {


        return ResponseEntity.ok().body("image has been deleted");
    }

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
