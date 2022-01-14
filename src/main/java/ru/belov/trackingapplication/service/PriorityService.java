package ru.belov.trackingapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.belov.trackingapplication.dao.PriorityDao;
import ru.belov.trackingapplication.model.Priority;

@Service
@Slf4j
public class PriorityService {

    private PriorityDao priorityDao;

    @Autowired
    public PriorityService(PriorityDao priorityDao) {
        this.priorityDao = priorityDao;
    }

    public List<Priority> findAll() {
        log.debug("Find all priority");
        return priorityDao.findAll();
    }

}
