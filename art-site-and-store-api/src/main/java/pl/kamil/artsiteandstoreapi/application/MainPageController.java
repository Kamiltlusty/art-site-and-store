package pl.kamil.artsiteandstoreapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.application.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.ports.incoming.DeleteImage;
import pl.kamil.artsiteandstoreapi.domain.usecase.FetchMainPageImagesImpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainPageController {
    private final FetchMainPageImagesImpl fmpIL;
    private final DeleteImage deleteUC;

    @GetMapping("/main-page")
    public ResponseEntity<Map<PlaceDTO, List<ImageDTO>>> getImagesMetadata() {
        var fetch = fmpIL.fetch();

        return ResponseEntity
                .ok()
                .body(fetch);
    }

    @DeleteMapping("/main-page/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable UUID id) {
        log.info("DELETE /api/main-page/{}", id);
        deleteUC.delete(id);

        return  ResponseEntity
                .ok()
                .build();
    }
}
