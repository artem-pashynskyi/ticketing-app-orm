package com.ticketingapp.controller;

import com.ticketingapp.dto.TaskDTO;
import com.ticketingapp.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/task")
public class TaskController {

//    @Autowired
//    ProjectService projectService;
//    @Autowired
//    UserService userService;
//    @Autowired
//    TaskService taskService;
//
//    @GetMapping("/create")
//    public String createTask(Model model) {
//        model.addAttribute("task", new TaskDTO());
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("employees", userService.findEmployees());
//        model.addAttribute("tasks", taskService.findAll());
//        return "task/create";
//    }
//
//    @PostMapping("/create")
//    public String insertTask(TaskDTO task) {
//        task.setTaskStatus(Status.OPEN);
//        task.setAssignedDate(LocalDateTime.now());
//        taskService.save(task);
//        return "redirect:/task/create";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteTask(@PathVariable("id") Long id) {
//        taskService.deleteById(id);
//        return "redirect:/task/create";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editTask(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("employees", userService.findEmployees());
//        model.addAttribute("tasks", taskService.findAll());
//        return "task/edit";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateTask(TaskDTO task) {
//        taskService.update(task);
//        return "redirect:/task/create";
//    }
//
//    @GetMapping("/employee/status")
//    public String statusTask(Model model) {
//        model.addAttribute("tasks", taskService.getNotCompleted());
//        return "employee/pending-task";
//    }
//
//    @GetMapping("/employee/status/edit/{id}")
//    public String statusEditTask(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("tasks", taskService.getNotCompleted());
//        model.addAttribute("statuses", Status.values());
//        return "employee/pending-task-edit";
//    }
//
//    @PostMapping("/employee/status/update/{id}")
//    public String statusUpdateTask(@PathVariable("id") Long id, TaskDTO task) {
//        taskService.findById(id).setTaskStatus(task.getTaskStatus());
//        return "redirect:/task/employee/status";
//    }
//
//    @GetMapping("/employee/archive")
//    public String archive(Model model) {
//        model.addAttribute("tasks", taskService.getCompleted());
//        return "employee/archived-projects";
//    }

}
