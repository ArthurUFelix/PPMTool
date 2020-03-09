package com.afelix.ppmtool.services;

import com.afelix.ppmtool.domain.Project;
import com.afelix.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        // Logic

        return projectRepository.save(project);
    }
}
