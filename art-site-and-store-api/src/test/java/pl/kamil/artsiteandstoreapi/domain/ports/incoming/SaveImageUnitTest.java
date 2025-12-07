package pl.kamil.artsiteandstoreapi.domain.ports.incoming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.exceptions.ImageSizeExceeded;
import pl.kamil.artsiteandstoreapi.domain.ports.outgoing.ImageRepository;
import pl.kamil.artsiteandstoreapi.domain.usecase.SaveImageImpl;
import pl.kamil.artsiteandstoreapi.domain.usecase.services.ImageMapper;
import pl.kamil.artsiteandstoreapi.infrastracture.utils.UUIDGeneratorImpl;
import pl.kamil.artsiteandstoreapi.infrastracture.utils.UUIDGeneratorTestImpl;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveImageUnitTest {

    @Mock
    ImageRepository ir;

    @Mock
    ImageMapper im;

    @Mock
    UUIDGeneratorImpl uuidGenerator;

    @InjectMocks
    SaveImageImpl si;

    UUID testUUID;

    @BeforeEach
    void setUp() {
        UUIDGeneratorTestImpl uuidGTI =  new UUIDGeneratorTestImpl();
        testUUID = uuidGTI.generateUUID();
    }

    @Test
    void shouldSaveImage() {
        // given
        ImageDTO imageDTO = createImageDTO();
        Image image = createImage();

        when(im.toImage(imageDTO)).thenReturn(image);
        when(uuidGenerator.generateUUID()).thenReturn(testUUID);
        when(ir.save(image)).thenReturn(image);
        // when
        Image result = si.save(imageDTO);
        // then
        assertEquals(image, result);
        verify(ir, times(1)).save(image);
    }

    @Test
    void shouldThrowExceptionImageSizeExceeded() {
        // given
        ImageDTO imageDTO = createImageDTO().withSize(15 * 1024 * 1024L);
        // when, then
        assertThrows(ImageSizeExceeded.class, () -> si.save(imageDTO));
    }

    public Image createImage() {
        return Image.builder()
                .imageId(testUUID)
                .fileName("abrakadabra.png")
                .path("/admin/" + testUUID)
                .mimeType("image/png")
                .size(1000L)
                .description("Opis")
                .width(128)
                .height(64)
                .build();
    }

    public static ImageDTO createImageDTO() {
        return ImageDTO.builder()
                .imageId(UUID.randomUUID())
                .fileName("abrakadabra.png")
                .path("")
                .mimeType("image/png")
                .size(1000L)
                .description("Opis")
                .width(128)
                .height(64)
                .build();
    }
}