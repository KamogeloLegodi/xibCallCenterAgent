package com.xib.assessment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import javax.validation.Valid;

@RestController
public class ManagerController {
	
	@Autowired
	private ManagerRepository managerRepository;

	// Create a manager
	@PostMapping("/manager")
	public Manager addManager(@Valid @RequestBody Manager manager) {
		return managerRepository.save(manager);
	}

}
