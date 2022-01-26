package User.CRUD.service;

import User.CRUD.dao.RoleDAO;
import User.CRUD.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return this.roleDAO.getRoleByName(name);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return this.roleDAO.getRoleById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List <Role> getAllRoles() {
        return this.roleDAO.allRoles();
    }


}
