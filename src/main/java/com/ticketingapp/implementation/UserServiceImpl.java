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
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public void delete(String username) {

    }
}
