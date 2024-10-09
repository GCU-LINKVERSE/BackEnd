package com.moim.moim.friend.domain;

import com.moim.moim.global.BaseTime;
import com.moim.moim.global.Status;
import com.moim.moim.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;

    private Long friendId;

    private String userPhoneNum;

    private String friendPhoneNum;

    private Status status;

    private boolean isFrom;

    public void acceptFriendRequest() {
        status = Status.DONE;
    }

    public void setFriendId(Long id) {
        friendId = id;
    }

}
