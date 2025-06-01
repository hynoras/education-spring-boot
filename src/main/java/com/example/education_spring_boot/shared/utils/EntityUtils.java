package com.example.education_spring_boot.shared.utils;

import com.example.education_spring_boot.shared.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class EntityUtils {
    /**
     * Check if the entity with given ID exists in the provided repository.
     *
     * @param repo The JPA repository to check.
     * @param id   The ID to look for.
     * @param <T>  Entity type
     * @param <ID> ID type
     * @throws ResourceNotFoundException if not found
     */
    public <T, ID> void isEntityExist(JpaRepository<T, ID> repo, ID id, String entityName) {
        repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No " + entityName + " with ID " + id + " found in database"));
    }
}
