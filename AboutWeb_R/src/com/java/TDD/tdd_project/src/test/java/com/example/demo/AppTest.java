package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {

    @Test
    public void create() {
        App app = new App();
        assertNotNull(app);
    }
}
