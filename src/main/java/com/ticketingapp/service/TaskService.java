package com.ticketingapp.service;

import com.ticketingapp.dto.TaskDTO;
import com.ticketingapp.entity.Task;

import java.util.List;

public interface TaskService {
    TaskDTO findById(Long id);
    List<TaskDTO> listAllTasks();
    Task save(TaskDTO taskDTO);
    void update(TaskDTO taskDTO);
    void delete(Long id);
}
