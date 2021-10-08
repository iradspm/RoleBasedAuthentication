package com.example.meetingwithroles.controller;

import com.example.meetingwithroles.model.Meeting;
import com.example.meetingwithroles.model.RoleUser.User;
import com.example.meetingwithroles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register_user")
    public String getUserRegistration(Model model)
    {
        model.addAttribute("user",new User());
        return "admin/register_user";
    }
    @PostMapping("/register_user")
    public String registerUser(User user)
    {
        //encrypt password
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encodedPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        userRepository.save(user);
        return "redirect:/admin/index/registered_users";
    }

    @GetMapping("/registered_users")
    private String showHomePage(Model model)
    {
        List<User> usersList=userRepository.findAll();
        model.addAttribute("usersList",usersList);
        // return "index";
        return "redirect:/admin/index/registered_users";
    }
    
    //admin
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public ModelAndView adminHome()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;

    }
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public ModelAndView userHome()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("userHome");
        return modelAndView;

    }
}
