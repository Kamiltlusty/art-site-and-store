package pl.kamil.artsiteandstoreapi.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.artsiteandstoreapi.application.port.ImageRepository;
import pl.kamil.artsiteandstoreapi.application.usecase.FetchMainPageImageListUC;
import pl.kamil.artsiteandstoreapi.domain.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.services.FetchImage;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FetchImageMetadataTest {
    @Mock
    FetchImage fi;

    @InjectMocks
    FetchMainPageImageListUC fmp;

    private final List<Image> imgList = List.of(new Image());
    private final int IMAGE_NUMBER = 3;
    private final String validUUID = "26929514-237c-11ed-861d-0242ac120002";
    private final UUID placeId = UUID.fromString(validUUID);

    @Test
    @DisplayName("This test checks whether method correctly aggregated " +
            "and invoked FetchImage class method fetch() to fetch images data and return list of it")
    public void shouldReturnListOfImageDTO() {
        // given
        when(fi.findAllPlaceIdByPageId(anyInt()))
                .thenReturn(List.of(placeId, placeId, placeId));
        when(fi.findAllByPlaceId(placeId))
                .thenReturn(imgList);
        // when
        List<ImageDTO> resultList = fmp.fetch();
        // then
        verify(fi, times(1))
                .findAllPlaceIdByPageId(anyInt());
        verify(fi, times(3))
                .findAllByPlaceId(placeId);
        assertEquals(IMAGE_NUMBER, resultList.size());
    }
}