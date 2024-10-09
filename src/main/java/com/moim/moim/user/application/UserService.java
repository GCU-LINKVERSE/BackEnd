package com.moim.moim.user.application;


import com.moim.moim.user.domain.User;
import com.moim.moim.user.domain.UserRepository;
import com.moim.moim.user.dto.UserRequestDto;
import com.moim.moim.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true )
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto request) {
        User user = User.from(request);
        return UserResponseDto.of(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("해당 아이디를 찾지 못했습니다."));
        userRepository.delete(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto request) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("해당 아이디를 찾지 못했습니다."));
        user.update(request);
        return UserResponseDto.of(user);
    }
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("해당 아이디를 찾지 못했습니다."));
        return UserResponseDto.of(user);
    }
}
