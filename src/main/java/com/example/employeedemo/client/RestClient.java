package com.example.employeedemo.client;

import com.example.employeedemo.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<AddressDTO> getEmployeeAddress(Long empId){
       return restTemplate.getForEntity("localhost:8080/rest/v1/address/{empId}",AddressDTO.class,new HttpEntity<>(empId,getHeaders()));
    }

    private HttpHeaders getHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
