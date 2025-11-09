package pl.kamil.artsiteandstoreapi.domain.entieties;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@With
@Table(name = "place")
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "page_id")
  private Page page;
}
