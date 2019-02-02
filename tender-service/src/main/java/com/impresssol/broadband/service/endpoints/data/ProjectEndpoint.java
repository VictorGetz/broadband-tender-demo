package com.impresssol.broadband.service.endpoints.data;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.xml.ws.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.repo.ProjectRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ProjectEndpoint {

	private final ProjectRepository projectRepository;

	@GetMapping("/projects")
	public List<Project> retrieveAllProjects() {
		return projectRepository.findAll();
	}

	@PostMapping("/projects")
	public ResponseEntity<Object> createProject(@RequestBody Project project) {
		Project savedProject = projectRepository.save(project);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedProject.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/projects/{id}")
	public Project getProject(@PathVariable long id) {
		try {
			return projectRepository.findById(id)
					.orElseThrow(() -> new RestClientException("Project could not be found in the Database id=" + id));
		} catch (Exception e) {
			log.info("Project not found", e);
			throw new RestClientException("Project could not be loaded from Database", e);
		}
	}

	@DeleteMapping("/projects/{id}")
	public ResponseEntity deleteProject(@PathVariable long id) {
		try {
			projectRepository.deleteById(id);
		} catch (Exception e) {
			log.info("Project could not be deleted", e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.accepted().body("Project deleted projectId=" + id);
	}

	@PutMapping("/projects/{id}")
	public ResponseEntity<Object> updateProject(@RequestBody Project project, @PathVariable long id) {
		if (!projectRepository.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		project.setId(id);
		projectRepository.save(project);
		return ResponseEntity.noContent().build();
	}
}
