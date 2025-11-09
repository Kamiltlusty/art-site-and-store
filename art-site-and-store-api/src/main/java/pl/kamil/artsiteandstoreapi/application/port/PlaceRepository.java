package pl.kamil.artsiteandstoreapi.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

import java.util.List;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
    List<UUID> findAllPlaceIdByPageId(Integer mainPageId);
}
