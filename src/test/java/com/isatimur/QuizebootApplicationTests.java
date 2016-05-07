package com.isatimur;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuizebootApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class QuizebootApplicationTests {

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void quize() {
        ResponseEntity<Quize> entity = restTemplate.getForEntity("http://localhost:9000/quizes/quize/0", Quize.class);
        assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();
    }

}
