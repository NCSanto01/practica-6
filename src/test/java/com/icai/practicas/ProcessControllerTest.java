package com.icai.practicas;

import com.icai.practicas.controller.ProcessController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void actualTest() {

        String address = "http://localhost:" + port + "/api/v1/process-step1";

        String fullNameRaw = "Nicolas Corsini";
        String dniRaw = "02783086V";
        String telefonoRaw = "+34 618068118";

        ProcessController.DataRequest data1 = new ProcessController.DataRequest(fullNameRaw, dniRaw, telefonoRaw);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(data1, headers);

        
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void legacyTest() {
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        //Datos correctos 
        MultiValueMap<String, String> data1 = new LinkedMultiValueMap<>();
        data1.add("fullName", "Nicolas Corsini");
        data1.add("dni", "02783086V");
        data1.add("telefono", "+34 619068118");

        //Nombre no válido 
        MultiValueMap<String, String> data2 = new LinkedMultiValueMap<>();
        data2.add("fullName", "Nicolas5555");
        data2.add("dni", "02783086V");
        data2.add("telefono", "+34 618068118");

        //DNI no válido 
        MultiValueMap<String, String> data3 = new LinkedMultiValueMap<>();
        data3.add("fullName", "Nicolas Corsini");
        data3.add("dni", "02783086VA");
        data3.add("telefono", "+34 618068118");

        //Teléfono no válido
        MultiValueMap<String, String> data4 = new LinkedMultiValueMap<>();
        data4.add("fullName", "Nicolas Corsini");
        data4.add("dni", "02783086V");
        data4.add("telefono", "qwertyuiop");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data1, headers);
        HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(data2, headers);
        HttpEntity<MultiValueMap<String, String>> request3 = new HttpEntity<>(data3, headers);
        HttpEntity<MultiValueMap<String, String>> request4 = new HttpEntity<>(data4, headers);
    
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
        ResponseEntity<String> result2 = this.restTemplate.postForEntity(address, request2, String.class);
        ResponseEntity<String> result3 = this.restTemplate.postForEntity(address, request3, String.class);
        ResponseEntity<String> result4 = this.restTemplate.postForEntity(address, request4, String.class);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result2.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result3.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result4.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
