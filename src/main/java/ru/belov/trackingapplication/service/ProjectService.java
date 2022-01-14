package ru.belov.trackingapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.belov.trackingapplication.dao.ProjectDao;
import ru.belov.trackingapplication.model.Project;

@Service
@Slf4j
public class ProjectService {

    private ProjectDao projectDao;

    @Autowired
    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public List<Project> findAll() {
        log.debug("Find all projects");
        return projectDao.findAll();
    }

    public void add(Project project) {
        log.debug("Add project with project = {}", project);
        projectDao.add(project);
    }

    public void delete(int id) {
        log.debug("Delete project with id = {}", id);
        projectDao.delete(id);
    }

}
