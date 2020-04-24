package com.xib.assessment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import javax.validation.Valid;

@RestController
public class AgentController {

	@Autowired
	private AgentRepository agentRepository;


	// Return all agents
	@GetMapping("/agents")
	public List<Agent> getAllAgents() {
		return agentRepository.findAll();
	}

	// Find agent based on Id
	@GetMapping("/agent/{id}")
	public ResponseEntity<Agent> getAgentById(@PathVariable(value = "id") Long agentID) throws Exception {
		Agent agent = agentRepository.findById(agentID)
				.orElseThrow(() -> new Exception("Oops! Agent with id:" + agentID + " is not found."));
		return ResponseEntity.ok().body(agent);
	}

	// Create an agent
	@PostMapping("/agent")
	public Agent createAgent(@Valid @RequestBody Agent agent) {
		return agentRepository.save(agent);
	}



}
