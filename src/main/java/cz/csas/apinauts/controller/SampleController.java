package cz.csas.apinauts.controller;

import cz.csas.apinauts.service.CsasRestAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Jarda Mlejnek on 03.12.2018.
 */
@RestController
@RequestMapping(SampleController.MAPPING)
public class SampleController {

	public static final String MAPPING = "/places";

	@Autowired
	private CsasRestAdapter csasRestAdapter;

	@RequestMapping(method = RequestMethod.GET)
	public Map getBranches() {
		ResponseEntity<Map> responseEntity = csasRestAdapter.execute("GET","/v3/places");
		return responseEntity.getBody();
	}
}
