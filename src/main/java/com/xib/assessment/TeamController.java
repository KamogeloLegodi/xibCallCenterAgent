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
import java.util.ArrayList;
import javax.validation.Valid;

@RestController
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private AgentRepository agentRepository;

	// Return all teams
	@GetMapping("/teams")
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	// Find a specific team based on provided Id
	@GetMapping("/team/{id}")
	public ResponseEntity<Team> getTeamById(@PathVariable(value = "id") Long teamId) throws Exception {
		Team team = teamRepository.findById(teamId)
				.orElseThrow(() -> new Exception("Oops! Team with id: " + teamId + " is not found!"));
		return ResponseEntity.ok().body(team);
	}

	// Create new team
	@PostMapping("/team")
	public Team createTeam(@Valid @RequestBody Team team) {
		return teamRepository.save(team);
	}

	// Assign an agent to a team 
	@PutMapping("/team/{id}/agent")
	public ResponseEntity<Agent> assignAgent(@PathVariable(value = "id") Long teamID,
			@Valid @RequestBody Agent agentDetails) throws Exception {
		Team team = teamRepository.findById(teamID).orElseThrow(() -> new Exception("Oops! id is not found :: " + teamID));

		agentDetails.setTeam(team);
		final Agent updatedAgent = agentRepository.save(agentDetails);
		return ResponseEntity.ok(updatedAgent);
	}

	// API returning a list of empty teams or agents
	@GetMapping("/emptyteams")
	public List<Team> getEmptyTeams() {

		List<Team> emptyTeams = new ArrayList<Team>();
		List<Team> agentTeams = new ArrayList<Team>();
        
		List<Team> teams = teamRepository.findAll();
		List<Agent> agents = agentRepository.findAll();
		
		for(int i = 0; i< teams.size(); i++) {
			for(int x = 0; x < agents.size(); x++){		
				if(teams.get(i).getId() == agents.get(x).getTeam().getId()){
					agentTeams.add(agents.get(x).getTeam());
				}
			}	
		}
		
		for(int i = 0; i< teams.size(); i++) {
			int countTeams = 0;
			int indexAt = -1;
			for(int x = 0; x < agentTeams.size(); x++ ){
				if(teams.get(i).getId() == agentTeams.get(x).getId() ) {
					countTeams++;
					indexAt = i;	
				}else {
					indexAt = i;
				}
			}
			if(countTeams == 0){
				emptyTeams.add(teams.get(indexAt));
			}
		}
		return emptyTeams;
	}
}
