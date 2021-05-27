package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


/*
서비스를 구축하는 영역이다.
UserApiLogicService
 */

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    // 실재 서비스를 담당하는 곳이므로 repository 가 필요한다.
    @Autowired
    private UserRepository userRepository;

    /*
    1. request data 를 받는다.
    2. request data 에 따라 user 를 생성한다.
    3. 생성된 데이터 -> userRepository 생성 return
     */



    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request 데이터를 가져옴
        UserApiRequest userApiRequest = request.getData();

        // 2. request 데이터에 따라 user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> UserApiResponse return

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        /*
        **** 이렇게 간단하게 처리할 수도 있다.

        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터가 없습니다."));
        */


        // id -> repository getOne, getById
        Optional<User> optionalUser = userRepository.findById(id);

        // user -> userApiResponse Return;

        return optionalUser
                .map(user ->
                        response(user)
                )
                .orElseGet(() ->        //  메소드를 한개 호출
                        Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data 를 가져오고
        UserApiRequest userApiRequest = request.getData();

        // 2. data 를 찾아서 update 시켜주고
        // 여기서의 user 기존의 데이터를 가진 user 가 아니라...
        // userApi"ReQuest" 에서 수정요청데이터를 가진 유저이다.
        Optional<User> optional = userRepository.findById(userApiRequest.getId());


        return optional.map(user -> {
            // 3. update
                user.setAccount(userApiRequest.getAccount())
                        .setPassword(userApiRequest.getPassword())
                        .setStatus(userApiRequest.getStatus())
                        .setEmail(userApiRequest.getEmail())
                        .setPhoneNumber(userApiRequest.getPhoneNumber())
                        .setRegisteredAt(userApiRequest.getRegisteredAt())
                        .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                        ;
                return user;

        })
        .map(user -> userRepository.save(user))     // 4. update: 업데이트 user 생성저장하고 user 리턴
        .map(user -> response(user))                // 5. userApiResponse 리턴
        .orElseGet(
                () -> Header.ERROR("데이터가 없음")
        );
    }

    @Override  // delete 는 주고 받는 것이 상관 없는가 보다.
    public Header delete(Long id) {
        // id 로 repository 에서
        Optional<User> optional = userRepository.findById(id);

        return optional
                .map(user -> {
                    userRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    private Header<UserApiResponse> response(User user){
        // user 객체로 userApiResponse 를 만드는 메소드
        // user 에 생성된 데이터들을 받는다.
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unRegisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}


