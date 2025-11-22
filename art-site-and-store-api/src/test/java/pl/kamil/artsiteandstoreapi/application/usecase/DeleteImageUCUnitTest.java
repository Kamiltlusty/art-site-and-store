package pl.kamil.artsiteandstoreapi.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.artsiteandstoreapi.application.exceptions.ImageNotFoundException;
import pl.kamil.artsiteandstoreapi.application.ports.DeleteImageUC;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.ports.ImageRepository;
import pl.kamil.artsiteandstoreapi.domain.ports.PlaceRepository;
import pl.kamil.artsiteandstoreapi.domain.services.ImageService;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteImageUCUnitTest {

    @Mock
    ImageRepository ir;

    @Mock
    PlaceRepository pr;

    @Test
    @DisplayName("verifies whether findById and deleteById methods were invoked")
    void shouldDeleteImageById() {
        // given
        UUID id = UUID.randomUUID();
        DeleteImageUC di = new DeleteImageUCImpl(new ImageService(ir, pr));
        when(ir.findById(id))
                .thenReturn(Optional.of(new Image().withImageId(id)));
        doNothing().when(ir).deleteById(id);
        // when
        di.delete(id);
        // then
        verify(ir, times(1)).findById(id);
        verify(ir, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("verifies whether findById was invoked and throws ImageNotFoundException on image absence")
    void shouldThrowImageNotFoundException() {
        // given
        UUID id = UUID.randomUUID();
        DeleteImageUC di = new DeleteImageUCImpl(new ImageService(ir, pr));
        when(ir.findById(id))
                .thenReturn(Optional.of(new Image().withImageId(id)));
        doThrow(ImageNotFoundException.class)
                .when(ir).deleteById(id);
        // when, then
        assertThrows(ImageNotFoundException.class, () -> di.delete(id));
        verify(ir, times(1)).findById(id);
    }
}
