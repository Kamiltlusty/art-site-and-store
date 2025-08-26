CREATE TABLE image
(
  image_id    UUID         NOT NULL,
  file_name    VARCHAR(255) NOT NULL,
  url         VARCHAR(255) NOT NULL,
  mime_type   VARCHAR(255) NOT NULL,
  size        BIGINT,
  description VARCHAR(255),
  width       INTEGER,
  height      INTEGER,
  CONSTRAINT pk_image PRIMARY KEY (image_id)
);

CREATE TABLE place
(
  place_id UUID         NOT NULL,
  name     VARCHAR(255) NOT NULL,
  CONSTRAINT pk_place PRIMARY KEY (place_id)
);

CREATE TABLE place_image
(
  place_image_id UUID NOT NULL,
  image_id       UUID NOT NULL,
  place_id       UUID NOT NULL,
  CONSTRAINT pk_place_image PRIMARY KEY (place_image_id)
);

ALTER TABLE place_image
  ADD CONSTRAINT fk_image_id FOREIGN KEY (image_id) REFERENCES image (image_id);

ALTER TABLE place_image
  ADD CONSTRAINT fk_place_id FOREIGN KEY (place_id) REFERENCES place (place_id) on DELETE CASCADE;
