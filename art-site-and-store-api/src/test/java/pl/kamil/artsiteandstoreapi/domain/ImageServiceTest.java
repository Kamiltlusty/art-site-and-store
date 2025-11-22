package pl.kamil.artsiteandstoreapi.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.artsiteandstoreapi.domain.ports.ImageRepository;
import pl.kamil.artsiteandstoreapi.domain.ports.PlaceRepository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.services.ImageService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {
    @Mock
    ImageRepository ir;

    @Mock
    PlaceRepository pr;

    @InjectMocks
    ImageService is;

    private final List<Image> imgList = List.of(new Image());
    private final String validUUID = "26929514-237c-11ed-861d-0242ac120002";
    private final UUID placeId = UUID.fromString(validUUID);

    @Test
    @DisplayName("invokes findByPlaceId method, should return Image")
    public void shouldReturnImage() {
        // given
        when(ir.findAllByPlace_PlaceId(placeId))
                .thenReturn(imgList);
        // when
        List<Image> result = is.findAllByPlaceId(placeId);
        // then
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("given MainPage id should return list of place ids")
    public void shouldReturnListOfPlaceIds() {
        // given
        when(pr.findAllPlaceIdByPage_PageId(anyInt()))
                .thenReturn(List.of(placeId, placeId, placeId));
        // when
        List<UUID> result = is.findAllPlaceIdByPageId(anyInt());
        // then
        int expected = 3;
        assertEquals(expected, result.size());
    }
}
