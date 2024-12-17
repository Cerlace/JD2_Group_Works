package itacademy.dao.impl;

import itacademy.api.TeacherDAO;
import itacademy.dao.DAO;
import itacademy.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeacherDAOImpl extends DAO<Teacher> implements TeacherDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherDAOImpl.class);

    public TeacherDAOImpl() {
        super(Teacher.class, LOGGER);
    }
}
