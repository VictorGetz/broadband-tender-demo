package com.impresssol.broadband.data.repo.district;

import org.springframework.data.repository.CrudRepository;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.entities.district.ContactPerson;

public interface ContactPersonRepository extends CrudRepository<ContactPerson, Long> {
}
