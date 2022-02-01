package User.CRUD.service;

import User.CRUD.model.User;
import User.CRUD.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    //    private final UserDAO userDAO;
//
//
//    @Autowired
//    public UserServiceImpl(UserDAO userDAO) {
//        this.userDAO = userDAO;
//
//    }
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void add(User user) {
        userRepository.save(user);
    }


    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void edit(User user) {
        userRepository.saveAndFlush(user);
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        return userRepository.getUserByUserName(userName);
//    }

}
