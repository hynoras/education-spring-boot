package com.example.education_spring_boot.utils.JSONUtils;

import com.example.education_spring_boot.exception.ResourceNotFoundException;
import com.example.education_spring_boot.model.entity.Location;
import com.example.education_spring_boot.repository.LocationRepo;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class LocationDeserializer extends StdDeserializer<Location> {
    private final LocationRepo locationRepo;

    public LocationDeserializer(LocationRepo locationRepo) {
        super(Location.class);
        this.locationRepo = locationRepo;
    }

    @Override
    public Location deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int locationId = ((IntNode) node).intValue();
        Optional<Location> location = locationRepo.findById((long) locationId);
        if (location.isEmpty()) {
            throw new ResourceNotFoundException("Location with id " + locationId + " can not be found!");
        }
        return location.get();
    }
}
