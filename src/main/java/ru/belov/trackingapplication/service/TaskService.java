package ru.belov.trackingapplication.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.belov.trackingapplication.dao.PriorityDao;
import ru.belov.trackingapplication.dao.ProjectDao;
import ru.belov.trackingapplication.dao.TaskDao;
import ru.belov.trackingapplication.dao.TopicDao;
import ru.belov.trackingapplication.dao.TypeDao;
import ru.belov.trackingapplication.dao.UserDao;
import ru.belov.trackingapplication.dto.TaskDto;
import ru.belov.trackingapplication.model.Task;

@Service
@Slf4j
public class TaskService {

    private ProjectDao projectDao;
    private TopicDao topicDao;
    private TypeDao typeDao;
    private PriorityDao priorityDao;
    private UserDao userDao;
    private TaskDao taskDao;

    @Autowired
    public TaskService(ProjectDao projectDao, TopicDao topicDao, TypeDao typeDao, PriorityDao priorityDao,
            UserDao userDao, TaskDao taskDao) {
        this.projectDao = projectDao;
        this.topicDao = topicDao;
        this.typeDao = typeDao;
        this.priorityDao = priorityDao;
        this.userDao = userDao;
        this.taskDao = taskDao;
    }

    public List<Task> findAll() {
        log.debug("Find all tasks");
        return taskDao.findAll()
                .stream()
                .map(taskDto -> new Task(
                        taskDto.getId(),
                        projectDao.findById(taskDto.getProjectId()),
                        topicDao.findById(taskDto.getTopicId()),
                        typeDao.findById(taskDto.getTypeId()),
                        userDao.findById(taskDto.getUserId()),
                        priorityDao.findById(taskDto.getPriorityId()),
                        taskDto.getDescription()))
                .collect(Collectors.toList());
    }

    public void add(TaskDto taskDto) {
        log.debug("Add new task");
        taskDao.add(taskDto);
    }

    public void delete(int id) {
        log.debug("Delete task with id = {}", id);
        taskDao.delete(id);
    }

    public List<Task> getTasksInProject(int id) {
        log.debug("Find task with projectId = {}", id);
        return taskDao.getTasksInProjects(id)
                .stream()
                .map(taskDto -> new Task(
                        taskDto.getId(),
                        projectDao.findById(taskDto.getProjectId()),
                        topicDao.findById(taskDto.getTopicId()),
                        typeDao.findById(taskDto.getTypeId()),
                        userDao.findById(taskDto.getUserId()),
                        priorityDao.findById(taskDto.getPriorityId()),
                        taskDto.getDescription()))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksForUser(int id) {
        log.debug("Find task with usertId = {}", id);
        return taskDao.getTasksForUser(id)
                .stream()
                .map(taskDto -> new Task(
                        taskDto.getId(),
                        projectDao.findById(taskDto.getProjectId()),
                        topicDao.findById(taskDto.getTopicId()),
                        typeDao.findById(taskDto.getTypeId()),
                        userDao.findById(taskDto.getUserId()),
                        priorityDao.findById(taskDto.getPriorityId()),
                        taskDto.getDescription()))
                .collect(Collectors.toList());
    }

}
