package pl.kamil.artsiteandstoreapi.domain.ports.incoming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.artsiteandstoreapi.application.dtos.ImageDTO;
import pl.kamil.artsiteandstoreapi.application.dtos.PlaceDTO;
import pl.kamil.artsiteandstoreapi.domain.entieties.Page;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;
import pl.kamil.artsiteandstoreapi.domain.ports.outgoing.ImageRepository;
import pl.kamil.artsiteandstoreapi.domain.ports.outgoing.PlaceRepository;
import pl.kamil.artsiteandstoreapi.domain.usecase.FetchMainPageImagesImpl;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.usecase.services.PlaceMapper;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageServiceMetadataUnitTest {

    @Mock
    private ImageRepository ir;

    @Mock
    private PlaceRepository pr;

    @InjectMocks
    FetchMainPageImagesImpl fmp;

    private final List<Image> imgList = List.of(new Image(), new Image(), new Image());
    private final int IMAGE_NUMBER = 3;
    private final String validUUID = "26929514-237c-11ed-861d-0242ac120002";
    private final UUID placeId = UUID.fromString(validUUID);

    @Test
    @DisplayName("This test checks whether method correctly aggregated " +
            "and invoked FetchImage class method fetch() to fetch images data and return list of it")
    public void shouldReturnListOfImageDTO() {
        // given
        Place place = new Place().withPlaceId(placeId);
        PlaceDTO placeDTO = PlaceMapper.toPlaceDTO(place);

        when(pr.findAllByPage_PageId(anyInt()))
                .thenReturn(List.of(place));
        when(ir.findAllByPlace_PlaceId(placeId))
                .thenReturn(imgList);
        // when
        Map<PlaceDTO, List<ImageDTO>> result = fmp.fetch();
        // then
        verify(pr, times(1))
                .findAllByPage_PageId(anyInt());
        verify(ir, times(1))
                .findAllByPlace_PlaceId(placeId);
        assertEquals(IMAGE_NUMBER, result.get(placeDTO).size());
    }
}