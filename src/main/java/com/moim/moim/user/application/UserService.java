package com.moim.moim.user.application;


import com.moim.moim.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true )
public class UserService {

}
