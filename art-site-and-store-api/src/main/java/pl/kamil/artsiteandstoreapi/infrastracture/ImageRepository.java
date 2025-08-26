package pl.kamil.artsiteandstoreapi.infrastracture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.kamil.artsiteandstoreapi.domain.entieties.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
