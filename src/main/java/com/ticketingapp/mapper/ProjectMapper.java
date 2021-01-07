package com.ticketingapp.mapper;

import com.ticketingapp.dto.ProjectDTO;
import com.ticketingapp.entity.Project;
import com.ticketingapp.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    private ModelMapper modelMapper;

    public ProjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProjectDTO convertToDTO(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    public Project convertToEntity(ProjectDTO projectDTO) {
        return modelMapper.map(projectDTO, Project.class);
    }
}
