package pl.kamil.artsiteandstoreapi.domain.ports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image,UUID> {
    List<Image> findAllByPlace_PlaceId(UUID placeId);
}
