package pl.kamil.artsiteandstoreapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.application.ports.DeleteImageUC;
import pl.kamil.artsiteandstoreapi.application.usecase.FetchMainPageImageListUC;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainPageController {
    private final FetchMainPageImageListUC fmpIL;
    private final DeleteImageUC deleteUC;

    @GetMapping("/main-page")
    public ResponseEntity<List<ImageDTO>> getImagesMetadata() {
        List<ImageDTO> fetch = fmpIL.fetch();

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
