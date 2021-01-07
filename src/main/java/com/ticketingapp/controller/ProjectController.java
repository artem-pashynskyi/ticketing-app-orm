package com.ticketingapp.controller;

import com.ticketingapp.dto.ProjectDTO;
import com.ticketingapp.dto.TaskDTO;
import com.ticketingapp.dto.UserDTO;
import com.ticketingapp.enums.Status;
import com.ticketingapp.service.ProjectService;
import com.ticketingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model) {
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("managers", userService.listAllByRole("manager"));
        return "project/create";
    }

    @PostMapping("/create")
    public String insertProject(ProjectDTO project) {
        projectService.save(project);
        project.setProjectStatus(Status.OPEN);
        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.delete(projectCode);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectcode}")
    public String completeProject(@PathVariable("projectcode") String projectcode) {
        projectService.complete(projectcode);
        return "redirect:/project/create";
    }

    @GetMapping("/edit/{projectcode}")
    public String editProject(@PathVariable("projectcode") String projectcode, Model model) {
        model.addAttribute("project", projectService.getByProjectCode(projectcode));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("managers", userService.listAllByRole("manager"));
        return "project/edit";
    }

    @PostMapping("/update/{projectcode}")
    public String updateProject(ProjectDTO project) {
        projectService.update(project);
        return "redirect:/project/create";
    }

//    @GetMapping("/manager/complete")
//    public String getProjectsByManager(Model model) {
//        UserDTO manager = userService.findById("john@gmail.com");
//        model.addAttribute("projects", getCountedListOfProjectDTO(manager));
//        return "manager/project-status";
//    }
//
//    @GetMapping("/manager/complete/{projectcode}")
//    public String completeProjectsForManagerById(@PathVariable("projectcode") String projectcode) {
//        projectService.findById(projectcode).setComplete(true);
//        return "redirect:/project/manager/complete";
//    }
//
//    private List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
//        return projectService.findAll()
//                .stream()
//                .filter(project -> project.getAssignedManager().equals(manager))
//                .peek(project -> {
//                    List<TaskDTO> tasks = taskService.findTasksByManager(manager);
//                    int completeCount = (int)tasks.stream()
//                            .filter(task -> task.getProject().equals(project) && task.getTaskStatus() == Status.COMPLETE).count();
//                    int inCompleteCount = (int)tasks.stream()
//                            .filter(task -> task.getProject().equals(project) && task.getTaskStatus() != Status.COMPLETE).count();
//                    project.setCompleteTasksCount(completeCount);
//                    project.setUnfinishedTasksCount(inCompleteCount);
//                }).collect(Collectors.toList());
//    }

}
