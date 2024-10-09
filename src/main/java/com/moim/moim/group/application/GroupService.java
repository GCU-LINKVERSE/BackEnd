package com.moim.moim.group.application;

import com.moim.moim.group.domain.Group;
import com.moim.moim.group.domain.GroupRepository;
import com.moim.moim.group.dto.GroupDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupDto createGroup(GroupDto groupDto) {
        Group group = new Group();
        group.setDate(groupDto.getDate());
        // 필요한 payer 정보 처리 로직 추가

        Group savedGroup = groupRepository.save(group);
        return convertToDto(savedGroup);
    }

    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public GroupDto getGroupById(Long id) {
        return groupRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    private GroupDto convertToDto(Group group) {
        GroupDto dto = new GroupDto();
        dto.setId(group.getId());
        dto.setDate(group.getDate());
        // 추가 필드 변환 로직이 있다면 추가
        return dto;
    }
}
