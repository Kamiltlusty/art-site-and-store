package pl.kamil.artsiteandstoreapi.application;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;
import pl.kamil.artsiteandstoreapi.infrastracture.PlaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

  @Mock
  PlaceRepository placeRepository;

  @InjectMocks
  ImageService imageService;

  @Nested
  @DisplayName("Tests for postImage()")
  class PostImageTest {

    @Test
    @DisplayName()
    void givenPlaceDTOAndImage_whenSaveInvoked_shouldReturnImageDTO() {

    }

  }

  @Nested
  @DisplayName("Tests for getCarousel()")
  class GetCarouselTest {
    @CsvSource("carousel, CAROUSEL, Carousel")
    @DisplayName("given correct placeName should return place")
    @ParameterizedTest
    void givenPlaceName_whenFindByNameInvoked_shouldReturnPlace(String placeName) {
      // given
      UUID uuid = UUID.randomUUID();
      Image img = new Image();
      List<Image> images = List.of(img, img, img, img, img, img);
      Optional<Place> place = Optional.ofNullable(Place.builder().placeId(uuid).name(placeName).images(images).build());

      when(placeRepository.findByName(placeName))
              .thenReturn(place);
      // when
      Place result = imageService.findByName(placeName);
      // then
      assertNotNull(result);
      assertEquals(uuid, result.getPlaceId());
      assertEquals(placeName, result.getName());
      assertTrue(images.size() <= result.getImages().size());

      verify(placeRepository, times(1)).findByName(placeName);
    }


    @NullSource
    @DisplayName("given name argument cannot be null")
    @ParameterizedTest
    void givenNullPlaceName_shouldReturnIllegalArgumentException(String placeName) {
      // given
      String msg = "name argument cannot be null";
      // when
      IllegalArgumentException actual = assertThrows(
              IllegalArgumentException.class, () -> imageService.findByName(placeName));
      // then
      assertEquals(IllegalArgumentException.class, actual.getClass());
      assertEquals(msg, actual.getMessage());
    }

    @CsvSource(value = {"''", "'   '", "inventory", "local place"})
    @DisplayName("given names which aren't in database method should throw IllegalArgumentException(name argument doesn't exists in database)")
    @ParameterizedTest
    void givenWrongPlaceName_shouldReturnEntityNotFoundException(String placeName) {
      // given
      String msg = "name argument doesn't exists in database";
      Exception expected = new EntityNotFoundException(msg);
      when(placeRepository.findByName(placeName))
              .thenThrow(expected);
      // when
      EntityNotFoundException actual = assertThrows(
              EntityNotFoundException.class, () -> imageService.findByName(placeName));
      // then
      assertEquals(expected, actual);
      assertEquals(msg, actual.getMessage());
    }
  }

}
