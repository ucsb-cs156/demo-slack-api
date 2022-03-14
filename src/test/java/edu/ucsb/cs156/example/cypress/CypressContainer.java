package edu.ucsb.cs156.example.cypress;

import javax.validation.constraints.NotNull;

import org.testcontainers.containers.GenericContainer;

public class CypressContainer extends GenericContainer<CypressContainer> {

    public static final String DEFAULT_IMAGE_AND_TAG = "cypress/included:3.3.1";
    public CypressContainer() {
        this(DEFAULT_IMAGE_AND_TAG);
    }
    public CypressContainer(@NotNull String image) {
        super(image);
    }
     
}

