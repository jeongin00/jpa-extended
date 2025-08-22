package com.example.demo.service;

import com.example.demo.controller.user.dto.*;
import com.example.demo.repository.user.AllocatedRepository;
import com.example.demo.repository.user.GroupRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.Allocated;
import com.example.demo.repository.user.entity.Group;
import com.example.demo.repository.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final AllocatedRepository allocatedrepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Transactional
    public GroupResponseDto findById(Integer id){
        // Group 조회
        Group group = groupRepository.findById(id)
                .orElseThrow(()->new RuntimeException("데이터베이스내 userid가 없습니다."));

        return GroupResponseDto.from(group);
    }

    @Transactional
    public List<GroupResponseDto> findAll(){
        return groupRepository.findAll()
                .stream()
                .map(GroupResponseDto::from)
                .toList();
    }

    @Transactional
    public GroupResponseDto save(GroupCreateRequestDto request){
        Group group = Group.create(
                request.getName(),
                request.getDesc()
        );
        Group created = groupRepository.save(group);
        return GroupResponseDto.from(created);
    }

    @Transactional
    public GroupResponseDto update(Integer groupId, GroupUpdateRequestDto request){
        // Group 조회
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("데이터베이스내 groupId"));

        // Users 조회
        List<User> users = request.getUserIds()
                .stream()
                .map((id)->userRepository
                        .findById(id)
                        .orElseThrow(()->new RuntimeException("데이터베이스 userid가 없어")))
                .toList();
        // 이제 allocated 옮겨야함..
        List<Allocated> allocates = users.stream()
                .map(user -> Allocated.create(group,user))
                .toList();
        allocatedrepository.saveAll(allocates);

        // group 갱신 및 저장 dirty checking
        group.update(request.getName(), request.getDesc());
        return GroupResponseDto.from(group);
    }

    @Transactional
    public void delete(Integer id){
        Group group = groupRepository.findById(id)
                .orElseThrow(()->new RuntimeException("그룹이 데이터베이스 내 존재하지 않습니다"));
        groupRepository.deleteById(id);
    }
}