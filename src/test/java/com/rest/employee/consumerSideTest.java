package com.rest.employee;

import com.rest.employee.model.dto.EmployeeDto;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 8090)
public class consumerSideTest {

    @Rule
    public StubRunnerRule runnerRule = new StubRunnerRule()
            .downloadStub("com.rest", "employee", "1-SNAPSHOT")
            .withPort(8083)
            .workOffline(true);

    @Test
    @Ignore
    public void given_WhenPassEvenNumberInQueryParam_ThenReturnEven() {

        RestTemplate template = new RestTemplate();

        ParameterizedTypeReference<HashMap<String, List<EmployeeDto>>> responseType =
                new ParameterizedTypeReference<HashMap<String, List<EmployeeDto>>>() {
                };

        RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8083/all"))
                .accept(MediaType.APPLICATION_JSON).build();
        Map<String, List<EmployeeDto>> jsonDictionary = template.exchange(request, responseType).getBody();

        System.out.println(jsonDictionary);
    }
}
