package com.moim.moim.controller;

import com.moim.moim.domain.Friend;
import com.moim.moim.service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping
    public Friend createFriend(@RequestBody Friend friend) {
        return friendService.saveFriend(friend);
    }

    @GetMapping
    public List<Friend> getAllFriends() {
        return friendService.getAllFriends();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friend> getFriendById(@PathVariable Long id) {
        return friendService.getFriendById(id)
                .map(friend -> ResponseEntity.ok(friend))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        friendService.deleteFriend(id);
        return ResponseEntity.noContent().build();
    }
}

