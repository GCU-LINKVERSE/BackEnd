package com.moim.moim.group.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class GroupDto {

    private Long id;
    private LocalDate date;
    private Long payerId;

}
