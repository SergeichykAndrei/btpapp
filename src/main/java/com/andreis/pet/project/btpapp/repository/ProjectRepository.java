package com.andreis.pet.project.btpapp.repository;

import com.andreis.pet.project.btpapp.model.enums.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
