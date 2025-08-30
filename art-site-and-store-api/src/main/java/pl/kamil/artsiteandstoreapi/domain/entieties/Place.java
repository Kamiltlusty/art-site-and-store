package pl.kamil.artsiteandstoreapi.domain.entieties;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@With
@Setter
@Getter
@Entity
@Builder
@EqualsAndHashCode(of = "placeId")
@NoArgsConstructor
@AllArgsConstructor
public class Place {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "place_id")
  private UUID placeId;

  @Column(nullable = false)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "place_image",
    joinColumns = @JoinColumn(name = "place_id"),
    inverseJoinColumns = @JoinColumn(name = "image_id")
  )
  private List<Image> images;
}
