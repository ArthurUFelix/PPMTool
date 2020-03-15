package com.afelix.ppmtool.services;

import com.afelix.ppmtool.domain.Project;
import com.afelix.ppmtool.exceptions.ProjectIdException;
import com.afelix.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Identificador do projeto '" + project.getProjectIdentifier().toUpperCase() + "' já existe");
        }

    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null)
            throw new ProjectIdException("Nenhum projeto com identificador '" + projectId + "' encontrado");

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null) {
            throw new ProjectIdException("Não é possível deletar o projeto de identificador '" + projectId + "'. Projeto não encontrado");
        }

        projectRepository.delete(project);
    }
}
