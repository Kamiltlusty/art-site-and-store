package pl.kamil.artsiteandstoreapi.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

import java.util.List;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place,UUID> {
    @Query("SELECT p.placeId from Place p WHERE p.page.pageId = :id")
    List<UUID> findAllPlaceIdByPage_PageId(@Param("id") Integer pageId);
}
