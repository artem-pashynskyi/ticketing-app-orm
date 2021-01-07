package com.ticketingapp.implementation;

import com.ticketingapp.dto.UserDTO;
import com.ticketingapp.entity.User;
import com.ticketingapp.mapper.UserMapper;
import com.ticketingapp.repository.UserRepository;
import com.ticketingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userEntities = userRepository.findAll(Sort.by("firstName"));
        return userEntities
                .stream()
                .map(user -> userMapper.convertToDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUserName(username);
        return userMapper.convertToDTO(user);
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUserName());
        User convertedUser = userMapper.convertToEntity(userDTO);
        convertedUser.setId(user.getId());
        userRepository.save(convertedUser);
        return findByUserName(userDTO.getUserName());
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    //Hard Delete
    @Override
    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> usersByRole = userRepository.findByRoleDescriptionIgnoreCase(role);
        return usersByRole
                .stream()
                .map(user -> userMapper.convertToDTO(user))
                .collect(Collectors.toList());
    }
}
