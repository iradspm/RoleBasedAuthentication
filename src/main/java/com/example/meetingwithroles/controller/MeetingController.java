package com.example.meetingwithroles.controller;

import com.example.meetingwithroles.model.Meeting;
import com.example.meetingwithroles.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MeetingController {

    @Autowired
    private MeetingRepository meetingRepository;

    @GetMapping("/")
    private String showHomePage(Model model)
    {
        List<Meeting> meetingList=meetingRepository.findAll();
        model.addAttribute("meetingList",meetingList);
        return "index";
       // return "admin/index";
    }
    @GetMapping("/meeting_page")
    public String showNewMeetingPage(Model model)
    {
        model.addAttribute("meeting",new Meeting());
        return "meeting_page";

    }

    @PostMapping("/save")
    public String saveMeeting(Meeting meeting)
    {
        meetingRepository.save(meeting);
        return "redirect:/";
    }
    @RequestMapping("/delete/{id}")
    public String deleteMeeting(@PathVariable(name = "id") Long id)
    {
        meetingRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String updateMeeting(@PathVariable(name = "id") Long id, Model model)
    {
//        ModelAndView mnv=new ModelAndView("edit_meeting");
//        Meeting meeting=meetingRepository.getById(id);
//        mnv.addObject("editMeeting",meeting);
//        return "redirect:/";
        Meeting meeting=meetingRepository.getById(id);
        model.addAttribute("meeting",meeting);
        return "edit_meeting";
    }

    @GetMapping("/meeting_schedule")
    public String meetingSchedule()
    {
        return "meeting_schedule";
    }
    @GetMapping("/login")
    public String viewLoginPage() {
        // custom logic before showing login page...
        return "CustomLogin/login";
    }
    @GetMapping("/logout")
    public String logout()
    {
        return "redirect:/CustomLogin/login";
    }

}
