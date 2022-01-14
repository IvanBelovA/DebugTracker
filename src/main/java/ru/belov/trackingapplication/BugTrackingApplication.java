package ru.belov.trackingapplication;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.belov.trackingapplication.config.SpringConfig;
import ru.belov.trackingapplication.service.PriorityService;
import ru.belov.trackingapplication.service.ProjectService;
import ru.belov.trackingapplication.service.TaskService;
import ru.belov.trackingapplication.service.TopicService;
import ru.belov.trackingapplication.service.TypeService;
import ru.belov.trackingapplication.service.UserService;
import ru.belov.trackingapplication.view.ApplicationViewer;

public class BugTrackingApplication {

    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        ApplicationViewer viewer = new ApplicationViewer(context.getBean(UserService.class),
                context.getBean(TaskService.class), context.getBean(ProjectService.class),
                context.getBean(TopicService.class), context.getBean(TypeService.class),
                context.getBean(PriorityService.class));
        viewer.runView();

        context.close();
    }

}
