package com.ticketingapp.implementation;

import com.ticketingapp.dto.ProjectDTO;
import com.ticketingapp.dto.UserDTO;
import com.ticketingapp.entity.Project;
import com.ticketingapp.entity.User;
import com.ticketingapp.enums.Status;
import com.ticketingapp.mapper.ProjectMapper;
import com.ticketingapp.mapper.UserMapper;
import com.ticketingapp.repository.ProjectRepository;
import com.ticketingapp.service.ProjectService;
import com.ticketingapp.service.TaskService;
import com.ticketingapp.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;
    private UserMapper userMapper;
    private UserService userService;
    private TaskService taskService;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserMapper userMapper, UserService userService, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO getByProjectCode(String code) {
        Project project = projectRepository.findByProjectCode(code);
        return projectMapper.convertToDTO(project);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> projects = projectRepository.findAll(Sort.by("projectCode"));
        return projects
                .stream()
                .map(projectMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.OPEN);
        Project project = projectMapper.convertToEntity(projectDTO);
        projectRepository.save(project);
    }

    @Override
    public void update(ProjectDTO projectDTO) {
        Project project = projectRepository.findByProjectCode(projectDTO.getProjectCode());
        Project convertedProject = projectMapper.convertToEntity(projectDTO);
        convertedProject.setProjectStatus(project.getProjectStatus());
        convertedProject.setId(project.getId());
        projectRepository.save(convertedProject);
    }

    @Override
    public void delete(String code) {
        Project project = projectRepository.findByProjectCode(code);
        project.setIsDeleted(true);
        project.setProjectCode(project.getProjectCode() + "-" + project.getId());
        projectRepository.save(project);
        taskService.deleteByProject(projectMapper.convertToDTO(project));
    }

    @Override
    public void complete(String code) {
        Project project = projectRepository.findByProjectCode(code);
        project.setProjectStatus(Status.COMPLETE);
        project.setComplete(true);
        projectRepository.save(project);
    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {
        UserDTO currentUserDTO = userService.findByUserName("john@gamil.com");
        User user = userMapper.convertToEntity(currentUserDTO);
        List<Project> projects = projectRepository.findByAssignedManager(user);
        return projects
                .stream()
                .map(project -> {
                    ProjectDTO projectDTO = projectMapper.convertToDTO(project);
                    projectDTO.setUnfinishedTasksCount(taskService.totalNonCompletedTasks(project.getProjectCode()));
                    projectDTO.setCompleteTasksCount(taskService.totalCompletedTasks(project.getProjectCode()));
                    return projectDTO;
                })
                .collect(Collectors.toList());
    }
}
