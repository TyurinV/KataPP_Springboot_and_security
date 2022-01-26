package User.CRUD.service;

import User.CRUD.dao.UserDAO;
import User.CRUD.dao.UserDAOImpl;
import User.CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userDAO;


    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;

    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }


    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }


    @Override
    @Transactional
    public void remove(Long id) {
        userDAO.remove(id);
    }


    @Override
    @Transactional
    public void edit(User user) {
        userDAO.edit(user);
    }


    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userDAO.getUserByName(userName);
    }

}
