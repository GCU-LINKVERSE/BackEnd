package com.moim.moim.friend.application;

import com.moim.moim.friend.domain.Friend;
import com.moim.moim.friend.domain.FriendRepository;
import com.moim.moim.friend.dto.WaitingFriendListDto;
import com.moim.moim.global.Status;
import com.moim.moim.user.domain.User;
import com.moim.moim.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    @Transactional
    public void createFriend(Long Id,String phoneNumber) throws NoSuchElementException{
        User toUser = userRepository.findById(Id).orElseThrow(()-> new NoSuchElementException("해당 아이디의 유저를 찾을 수 없습니다."));
        if(!userRepository.existsByPhoneNumber(phoneNumber)){
            NoSuchElementException ex = new NoSuchElementException("해당 사용자를 찾을 수 없습니다");
        }
        User fromUser = userRepository.findByPhoneNumber(phoneNumber);

        Friend friendFrom = Friend.builder()
                .users(fromUser)
                .status(Status.PENDING)
                .userPhoneNum(fromUser.getPhoneNumber())
                .friendPhoneNum(toUser.getPhoneNumber())
                .isFrom(true)
                .build();

        Friend friendTo = Friend.builder()
                .users(toUser)
                .status(Status.PENDING)
                .userPhoneNum(toUser.getPhoneNumber())
                .friendPhoneNum(fromUser.getPhoneNumber())
                .isFrom(false)
                .build();

        fromUser.getFriendList().add(friendTo);
        toUser.getFriendList().add(friendFrom);

        friendRepository.save(friendFrom);
        friendRepository.save(friendTo);

        friendTo.setFriendId(friendFrom.getId());
        friendFrom.setFriendId(friendTo.getId());

    }

    @Transactional
    public ResponseEntity<?> getWaitingFriendList(Long id) throws Exception{
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException(("해당 아이디의 사용자를 찾을 수 없습니다.")));
        List<Friend> friendList = user.getFriendList();
        List<WaitingFriendListDto> result = new ArrayList<>();

        for (Friend x : friendList) {
            if(!x.isFrom() && x.getStatus() == Status.PENDING) {
                User friend = userRepository.findByPhoneNumber(x.getFriendPhoneNum());
                WaitingFriendListDto dto = WaitingFriendListDto.builder()
                        .Id(x.getId())
                        .phoneNumber(friend.getPhoneNumber())
                        .status(x.getStatus())
                        .build();
                result.add(dto);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Transactional
    public String approveFriendRequest(Long friendId) throws Exception{
        Friend friend = friendRepository.findById(friendId).orElseThrow(()->new NoSuchElementException("해당 아이디의 사용자가 없습니다."));
        Friend oppofriend = friendRepository.findById(friend.getFriendId()).orElseThrow(()-> new NoSuchElementException("해당 아이디의 사용자를 찾을 수 없습니다."));

        friend.acceptFriendRequest();;
        oppofriend.acceptFriendRequest();

        return "승인 성공";
    }

}
