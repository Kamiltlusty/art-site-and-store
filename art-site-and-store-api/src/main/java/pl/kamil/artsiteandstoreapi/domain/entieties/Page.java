package pl.kamil.artsiteandstoreapi.domain.entieties;

import jakarta.persistence.*;
import lombok.*;

@With
@Table(name = "page")
@Setter
@Getter
@Entity
@Builder
@EqualsAndHashCode(of = "pageId")
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    @Id
    @Column(name = "page_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pageId;
}
