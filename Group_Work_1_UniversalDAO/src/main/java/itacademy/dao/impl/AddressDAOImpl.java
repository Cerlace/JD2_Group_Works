package itacademy.dao.impl;

import itacademy.api.AddressDAO;
import itacademy.dao.UniversalDAO;
import itacademy.dto.Address;

public class AddressDAOImpl extends UniversalDAO<Address> implements AddressDAO {
    public AddressDAOImpl() {
        super(Address.class);
    }
}
