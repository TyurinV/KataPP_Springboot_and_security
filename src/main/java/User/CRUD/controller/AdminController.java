package User.CRUD.controller;

import User.CRUD.model.Role;
import User.CRUD.model.User;
import User.CRUD.service.RoleService;
import User.CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("userList", users);
        return "allusers";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam(required = false) String roleAdmin) { // массив айдишников
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(2l));
        if (roleAdmin != null) {
            roles.add(roleService.getRoleById(1l));
        }
        user.setRoles(roles);
        userService.edit(user);

//        Set<Role> roles = new HashSet<>();
//        // едит юзеров и масссив
//        roles.add(roleService.getRoleById(2l));
//        if (roleAdmin != null) {
//            roles.add(roleService.getRoleById(1l));
//        }
//        user.setRoles(roles);
//        userService.edit(user);
        return "redirect:/admin/";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("user", new User());
        return "/add";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(required = false) String roleAdmin) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(2l));
        if (roleAdmin != null) {
            roles.add(roleService.getRoleById(1l));
        }
        user.setRoles(roles);
        user.setPassword(hashedPassword);
        userService.add(user);
        return "redirect:/admin/";
    }


    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.remove(id);
        return "redirect:/admin/";
    }
}
