package ru.belov.trackingapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.belov.trackingapplication.dao.TopicDao;
import ru.belov.trackingapplication.model.Topic;

@Service
@Slf4j
public class TopicService {

    private TopicDao topicDao;

    public TopicService(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public List<Topic> findAll() {
        log.debug("Find all topics");
        return topicDao.findAll();
    }

}
