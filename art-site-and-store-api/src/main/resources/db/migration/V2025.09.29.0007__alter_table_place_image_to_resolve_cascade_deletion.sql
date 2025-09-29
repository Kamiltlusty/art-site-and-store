ALTER TABLE place_image
DROP CONSTRAINT IF EXISTS fk_image_id;

ALTER TABLE place_image
    ADD CONSTRAINT fk_image_id
        FOREIGN KEY (image_id)
            REFERENCES image(image_id)
            ON DELETE CASCADE;