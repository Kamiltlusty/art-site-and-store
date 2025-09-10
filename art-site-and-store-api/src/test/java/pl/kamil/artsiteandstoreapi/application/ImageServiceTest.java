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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.artsiteandstoreapi.application.exceptions.ImageNotFoundException;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;
import pl.kamil.artsiteandstoreapi.infrastracture.ImageRepository;
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

  @Mock
  ImageRepository imageRepository;

  @InjectMocks
  ImageService imageService;

  @Nested
  @DisplayName("Tests for postImage()")
  class PostImageTests {

    @Test
    @DisplayName("")
    void givenPlaceDTOAndImage_whenSaveInvoked_shouldReturnImageDTO() {

    }

  }

  @Nested
  @DisplayName("Tests for delete")
  class DeleteImageTests {

    @Test
    @DisplayName("given correct ImageId should call delete on image repository")
    void givenImageDTO_whenDeleteInvoked_shouldCallRepositoryDelete() {
      // given
      UUID uuid = UUID.randomUUID();
      doNothing().when(imageRepository).deleteByImageId(any(UUID.class));
      // when
      imageService.deleteByImageId(uuid);
      // then
      verify(imageRepository, times(1))
              .deleteByImageId(uuid);
    }

    @NullSource
    @DisplayName("given null ImageId should throw NullPointerException")
    @ParameterizedTest
    void givenNullImageId_shouldThrowNullPointerException(UUID uuid) {
      // given
      String msg = "ImageId cannot be null";
      // when, then
      NullPointerException actual = assertThrows(NullPointerException.class,
              () -> imageService.deleteByImageId(uuid));
      assertEquals(msg, actual.getMessage());
    }

    @DisplayName("given incorrect ImageId should throw ImageNotFoundException")
    @Test
    void givenIncorrectImageId_shouldImageNotFoundException() {
      // given
      UUID uuid = UUID.randomUUID();
      doThrow(EntityNotFoundException.class)
              .when(imageRepository)
              .deleteByImageId(uuid);
      // when, then
      ImageNotFoundException actual = assertThrows(ImageNotFoundException.class,
              () -> imageService.deleteByImageId(uuid));
      verify(imageRepository, times(1))
              .deleteByImageId(uuid);
      assertEquals(uuid, actual.getUUID());
    }
  }

  @Nested
  @DisplayName("Tests for getCarousel()")
  class GetCarouselTests {

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
    @DisplayName("given null should throw NullPointerException")
    @ParameterizedTest
    void givenNullPlaceName_shouldReturnNullPointerException(String placeName) {
      // given
      String msg = "name argument cannot be null";
      // when, then
      NullPointerException actual = assertThrows(
              NullPointerException.class, () -> imageService.findByName(placeName));
      verify(placeRepository, times(0)).findByName(placeName);
      assertEquals(msg, actual.getMessage());
    }

    @CsvSource(value = {"''", "'   '"})
    @DisplayName("given incorrect names should throw IllegalArgumentException")
    @ParameterizedTest
    void givenWrongPlaceName_shouldReturnIllegalArgumentException(String placeName) {
      // given, when, then
      String msg = "name argument cannot be empty";
      Exception actual = assertThrows(
              IllegalArgumentException.class, () -> imageService.findByName(placeName));
      verify(placeRepository, times(0)).findByName(placeName);
      assertEquals(msg, actual.getMessage());
    }
  }
}
