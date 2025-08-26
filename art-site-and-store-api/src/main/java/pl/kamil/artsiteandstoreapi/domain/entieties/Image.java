package pl.kamil.artsiteandstoreapi.domain.entieties;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID image_id;

  @Column(nullable = false)
  private String filename;

  @Column(nullable = false)
  private String url;

  @Column(nullable = false)
  private String mime_type;

  private Long size;
  private String description;
  private Integer width;
  private Integer height;




}
