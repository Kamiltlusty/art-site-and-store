DROP table if exists image;
DROP table if exists place;
DROP table if exists page;

CREATE TABLE page (
    page_id INTEGER NOT NULL,
    PRIMARY KEY (page_id)
);

CREATE TABLE place (
    place_id UUID NOT NULL,
    name VARCHAR(64) NOT NULL,
    page_id INTEGER NOT NULL,
    PRIMARY KEY (place_id),
    FOREIGN KEY (page_id) REFERENCES page(page_id)
);

CREATE TABLE image (
    image_id UUID NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL,
    mime_type VARCHAR(64) NOT NULL,
    size BIGINT,
    description TEXT,
    width INTEGER,
    height INTEGER,
    place_id UUID NOT NULL,
    PRIMARY KEY (image_id),
    FOREIGN KEY (place_id) REFERENCES place(place_id)
);



