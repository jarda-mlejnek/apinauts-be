package cz.csas.apinauts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Jarda Mlejnek on 03.12.2018.
 */
@Service
public class CsasRestAdapter {

	@Value("${csas.baseUrl}")
	private String baseUrl;

	@Value("${csas.web-api-key}")
	private String webApiKey;

	@Autowired
	private RestTemplate restTemplate;

	public <T, S> ResponseEntity<T> execute(String httpMethod, String endpointUrl) {
		return execute(httpMethod, endpointUrl, null);
	}

	public <T, S> ResponseEntity<T> execute(String httpMethod, String endpointUrl, S body) {
		HttpEntity<S> entity = new HttpEntity<>(body, getCsasHeaders());
		UriComponentsBuilder builder =  UriComponentsBuilder.fromHttpUrl(baseUrl + endpointUrl);

		ResponseEntity<T> responseEntity = restTemplate.exchange(builder.toUriString(),
				HttpMethod.resolve(httpMethod),
				entity,
				new ParameterizedTypeReference<T>() {});

		return responseEntity;
	}

	private HttpHeaders getCsasHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("WEB-API-key", webApiKey);
		return httpHeaders;
	}
}
