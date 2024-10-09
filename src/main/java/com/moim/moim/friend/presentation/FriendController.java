package com.moim.moim.friend.presentation;

import com.moim.moim.friend.application.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @PostMapping("/user/{id}/friends/{phoneNumber}")
    public String sendFriendRequest(@PathVariable Long id,@PathVariable String phoneNumber){
        friendService.createFriend(id, phoneNumber);

        return "success";
    }

    @GetMapping("/user/{id}/friends/received")
    public ResponseEntity<?> getWaitingFriendList(@PathVariable Long id) throws Exception{
        return friendService.getWaitingFriendList(id);
    }

    @PostMapping("/user/firends/approve/{friendId}")
    public String approveFriend(@PathVariable Long friendId) throws Exception{
        return friendService.approveFriendRequest(friendId);
    }
}
