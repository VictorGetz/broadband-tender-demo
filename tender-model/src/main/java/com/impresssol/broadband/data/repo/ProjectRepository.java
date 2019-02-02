package com.impresssol.broadband.data.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.impresssol.broadband.data.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	@Override
	List<Project> findAll();
}
