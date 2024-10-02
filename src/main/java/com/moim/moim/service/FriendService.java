package com.moim.moim.service;

import com.moim.moim.domain.Friend;
import com.moim.moim.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public Friend saveFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    public List<Friend> getAllFriends() {
        return friendRepository.findAll();
    }

    public Optional<Friend> getFriendById(Long id) {
        return friendRepository.findById(id);
    }

    public void deleteFriend(Long id) {
        friendRepository.deleteById(id);
    }
}


