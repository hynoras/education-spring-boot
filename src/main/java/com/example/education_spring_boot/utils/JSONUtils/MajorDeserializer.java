package com.example.education_spring_boot.utils.JSONUtils;

import com.example.education_spring_boot.exception.ResourceNotFoundException;
import com.example.education_spring_boot.model.entity.Major;
import com.example.education_spring_boot.repository.MajorRepo;
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
public class MajorDeserializer extends StdDeserializer<Major> {
    private final MajorRepo majorRepo;

    public MajorDeserializer(MajorRepo majorRepo) {
        super(Major.class);
        this.majorRepo = majorRepo;
    }

    @Override
    public Major deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int majorId = ((IntNode) node).intValue();
        Optional<Major> major = majorRepo.findById((long) majorId);
        if (major.isEmpty()) {
            throw new ResourceNotFoundException("Major with id " + majorId + " can not be found!");
        }
        return major.get();
    }
}
