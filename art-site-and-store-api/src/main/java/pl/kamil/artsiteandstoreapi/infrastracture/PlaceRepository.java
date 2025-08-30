package pl.kamil.artsiteandstoreapi.infrastracture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Place;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
  Optional<Place> findByName(String name);
}
