package ru.belov.trackingapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.belov.trackingapplication.dao.TypeDao;
import ru.belov.trackingapplication.model.Type;

@Service
@Slf4j
public class TypeService {

    private TypeDao typeDao;

    public TypeService(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public List<Type> findAll() {
        log.debug("Find all types");
        return typeDao.findAll();
    }

}
