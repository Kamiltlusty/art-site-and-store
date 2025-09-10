package pl.kamil.artsiteandstoreapi.infrastracture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
  Optional<Image> findByImageId(UUID uuid);

  void deleteByImageId(UUID imageId);
}
