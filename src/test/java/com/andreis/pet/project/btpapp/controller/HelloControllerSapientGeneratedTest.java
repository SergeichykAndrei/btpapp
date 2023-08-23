package com.andreis.pet.project.btpapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

class HelloControllerSapientGeneratedTest {

    //Sapient generated method id: ${a89e5826-8bb5-3ecb-84a4-25224a8efa9c}
    @Test()
    void helloTest() {
        HelloController target = new HelloController();
        ResponseEntity<String> result = target.hello();
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Hello World!", HttpStatus.OK);
        assertAll("result", () -> assertThat(result, equalTo(responseEntity)));
    }
}
