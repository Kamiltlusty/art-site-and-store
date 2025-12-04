package pl.kamil.artsiteandstoreapi.infrastracture.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGeneratorTestImpl implements UUIDGenerator {
    @Override
    public UUID generateUUID() {
        return UUID.fromString("14c9107a-8022-43b9-b800-52150ef370d5");
    }
}
