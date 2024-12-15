package itacademy.dao.impl;

import itacademy.api.CourseDAO;
import itacademy.dao.DAO;
import itacademy.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseDAOImpl extends DAO<Course> implements CourseDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDAOImpl.class);

    public CourseDAOImpl() {
        super(Course.class, LOGGER);
    }
}
