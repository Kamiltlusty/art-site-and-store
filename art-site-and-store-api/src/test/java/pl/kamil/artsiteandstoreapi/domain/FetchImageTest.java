package pl.kamil.artsiteandstoreapi.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.artsiteandstoreapi.application.port.ImageRepository;
import pl.kamil.artsiteandstoreapi.application.port.PlaceRepository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.services.FetchImage;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchImageTest {
    @Mock
    ImageRepository ir;

    @Mock
    PlaceRepository pr;

    @InjectMocks
    FetchImage fi;

    private final List<Image> imgList = List.of(new Image());
    private final String validUUID = "26929514-237c-11ed-861d-0242ac120002";
    private final UUID placeId = UUID.fromString(validUUID);

    @Test
    @DisplayName("invokes findByPlaceId method, should return Image")
    public void shouldReturnImage() {
        // given
        when(ir.findAllByPlaceId(placeId))
                .thenReturn(imgList);
        // when
        List<Image> result = fi.findAllByPlaceId(placeId);
        // then
        assertFalse(result.isEmpty());
    }

    private final Integer MAIN_PAGE_ID = 1;
    @Test
    @DisplayName("given MainPage id should return list of place ids")
    public void shouldReturnListOfPlaceIds() {
        // given
        when(pr.findAllPlaceIdByPageId(MAIN_PAGE_ID))
                .thenReturn(List.of(placeId, placeId, placeId));
        // when
        List<UUID> result = fi.findAllPlaceIdByPageId();
        // then
        int expected = 3;
        assertEquals(expected, result.size());
    }
}
