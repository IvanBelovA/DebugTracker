package ru.belov.trackingapplication.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import ru.belov.trackingapplication.dto.TaskDto;
import ru.belov.trackingapplication.exceptions.ReaderExceptions;
import ru.belov.trackingapplication.model.Priority;
import ru.belov.trackingapplication.model.Project;
import ru.belov.trackingapplication.model.Task;
import ru.belov.trackingapplication.model.Topic;
import ru.belov.trackingapplication.model.Type;
import ru.belov.trackingapplication.model.User;
import ru.belov.trackingapplication.service.PriorityService;
import ru.belov.trackingapplication.service.ProjectService;
import ru.belov.trackingapplication.service.TaskService;
import ru.belov.trackingapplication.service.TopicService;
import ru.belov.trackingapplication.service.TypeService;
import ru.belov.trackingapplication.service.UserService;

@Component
public class ApplicationViewer {

    private static final String SEPARATOR = System.lineSeparator();
    private static final String FORMATTER = "ID: %d | %s |";
    private static final String TASK_FORMATER = "ID: %d | %s | %s | %s | %s | %s %s | %s |";
    private static final String USER_FORMATER = "ID: %d | %s | %s | %s |";
    private static final String PROJECT_FORMATER = "ID: %d, Project name: %s";

    private UserService userService;
    private TaskService taskService;
    private ProjectService projectService;
    private TopicService topicService;
    private TypeService typeService;
    private PriorityService priorityService;

    @Autowired
    public ApplicationViewer(UserService userService, TaskService taskService, ProjectService projectService,
            TopicService topicService, TypeService typeService, PriorityService priorityService) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.topicService = topicService;
        this.typeService = typeService;
        this.priorityService = priorityService;
    }

    public void runView() {
        String command = "";
        while (!command.equals("exit")) {
            try {
                showMenu();
                command = getCommand();

                if (command.equalsIgnoreCase("a")) {
                    runCreateView();
                } else if (command.equalsIgnoreCase("b")) {
                    runDeleteView();
                } else if (command.equalsIgnoreCase("c")) {
                    viewUsers(userService.findAll());
                } else if (command.equalsIgnoreCase("d")) {
                    viewProjects(projectService.findAll());
                } else if (command.equalsIgnoreCase("e")) {
                    viewProjects(projectService.findAll());
                    viewTasks(taskService.getTasksInProject(Integer.valueOf(userInput("Enter project id from list"))));
                } else if (command.equalsIgnoreCase("f")) {
                    viewUsers(userService.findAll());
                    viewTasks(taskService.getTasksForUser(Integer.valueOf(userInput("Enter user id from list"))));
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter is not numeric, try again");
            }
        }
    }

    private void runDeleteView() {
        try {
            String command = "";
            while (!command.equals("return")) {
                showEntityMenu();
                command = getCommand();
                try {
                    if (command.equalsIgnoreCase("a")) {
                        viewUsers(userService.findAll());
                        userService.delete(Integer.valueOf(userInput("Enter user id from list")));
                    } else if (command.equalsIgnoreCase("b")) {
                        viewProjects(projectService.findAll());
                        projectService.delete(Integer.valueOf(userInput("Enter project id from list")));
                    } else if (command.equalsIgnoreCase("c")) {
                        viewTasks(taskService.findAll());
                        taskService.delete(Integer.valueOf(userInput("Enter project id from list")));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You enter is not numeric, try again");
                }
            }
        } catch (DataIntegrityViolationException e) {
            System.out.println("You enter not exist id");
        }
    }

    private void runCreateView() {
        try {
            String command = "";
            while (!command.equals("return")) {
                showEntityMenu();
                command = getCommand();

                try {
                    if (command.equalsIgnoreCase("a")) {
                        userService.add(new User(userInput("Enter name"), userInput("Enter last name"),
                                userInput("Enter speciality")));
                    } else if (command.equalsIgnoreCase("b")) {
                        projectService.add(new Project(userInput("Enter project name")));
                    } else if (command.equalsIgnoreCase("c")) {
                        taskService.add(inputDto());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You enter is not numeric, try again");
                }
            }
        } catch (DataIntegrityViolationException e) {
            System.out.println("You enter inncorrect id, because the object depends");
        }
    }

    private void showMenu() {
        String menu = SEPARATOR + "Enter one of the below commands:" + SEPARATOR
                + "a. Create entity" + SEPARATOR
                + "b. Delete entity" + SEPARATOR
                + "c. Get list of all users" + SEPARATOR
                + "d. Get list of all projects" + SEPARATOR
                + "e. Get list of all tasks in the project" + SEPARATOR
                + "f. Get list of all tasks assigned to user" + SEPARATOR
                + "exit. For exit" + SEPARATOR;

        System.out.println(menu);
    }

    private void showEntityMenu() {
        String entityMenu = SEPARATOR + "Enter one of the below entities:" + SEPARATOR
                + "a. Users" + SEPARATOR
                + "b. Projects" + SEPARATOR
                + "c. Tasks" + SEPARATOR
                + "return. Returning main menu" + SEPARATOR;

        System.out.println(entityMenu);
    }

    private String getCommand() {
        System.out.println("Waiting command:");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            throw new ReaderExceptions("Impossible read command");
        }
    }

    private String userInput(String input) {
        System.out.println(input);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            throw new ReaderExceptions("Impossible read command");
        }
    }

    private void viewUsers(List<User> users) {
        System.out.println(SEPARATOR + "Users:");

        for (User user: users) {
            System.out.println(
                    String.format(USER_FORMATER,
                            user.getId(), user.getName(), user.getLastName(), user.getSpeciality()));

        }
    }

    private void viewProjects(List<Project> projects) {
        System.out.println(SEPARATOR + "Projects:");

        for (Project project: projects) {
            System.out.println(String.format(PROJECT_FORMATER, project.getId(), project.getName()));
        }
    }

    private void viewTasks(List<Task> tasks) {
        System.out.println(SEPARATOR + "Tasks:");

        for (Task task: tasks) {
            System.out.println(String.format(TASK_FORMATER,
                    task.getId(), task.getProject().getName(), task.getTopic().getTitle(), task.getType().getTitle(),
                    task.getPriority().getName(), task.getUser().getName(), task.getUser().getLastName(),
                    task.getDescription()));
        }
    }

    private void viewType(List<Type> types) {
        System.out.println(SEPARATOR + "Types:");

        for (Type type: types) {
            System.out.println(String.format(FORMATTER, type.getId(), type.getTitle()));
        }
    }

    private void viewTopic(List<Topic> topics) {
        System.out.println(SEPARATOR + "Topics:");

        for (Topic topic: topics) {
            System.out.println(String.format(FORMATTER, topic.getId(), topic.getTitle()));
        }
    }

    private void viewPriority(List<Priority> priorityes) {
        System.out.println(SEPARATOR + "Priorityes:");

        for (Priority priority: priorityes) {
            System.out.println(String.format(FORMATTER, priority.getId(), priority.getName()));
        }
    }

    private TaskDto inputDto() {
        TaskDto taskDto = new TaskDto();

        viewProjects(projectService.findAll());
        taskDto.setProjectId(Integer.valueOf(userInput("Enter project id")));

        viewTopic(topicService.findAll());
        taskDto.setTopicId(Integer.valueOf(userInput("Enter topic id")));

        viewType(typeService.findAll());
        taskDto.setTypeId(Integer.valueOf(userInput("Enter type id")));

        viewPriority(priorityService.findAll());
        taskDto.setPriorityId(Integer.valueOf(userInput("Enter priority id")));

        viewUsers(userService.findAll());
        taskDto.setUserId(Integer.valueOf(userInput("Enter user id")));

        taskDto.setDescription(userInput("Enter description"));
        return taskDto;
    }

}
