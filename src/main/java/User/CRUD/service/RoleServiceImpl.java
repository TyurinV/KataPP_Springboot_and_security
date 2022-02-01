package User.CRUD.service;
import User.CRUD.model.Role;
import User.CRUD.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

//    private final RoleDAO roleDAO;
//
//    @Autowired
//    public RoleServiceImpl(RoleDAO roleDAO) {
//        this.roleDAO = roleDAO;
//    }

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return this.roleRepository.findRoleByName(name);
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }


    @Override
    public List <Role> getAllRoles() {
        return this.roleRepository.findAll();
    }


}
