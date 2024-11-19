package itacademy.dao.impl;

import itacademy.api.PeopleDAO;
import itacademy.dao.UniversalDAO;
import itacademy.dto.People;

public class PeopleDAOImpl extends UniversalDAO<People> implements PeopleDAO {

    public PeopleDAOImpl() {
        super(People.class);
    }
}
