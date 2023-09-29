package com.example.kakao.user;

import com.example.kakao._core.utils.JwtTokenUtils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception500;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public void join(UserRequest.JoinDTO requestDTO) {

        // 다른 방법
        /*
         * List<User> checkingUser = userJPARepository.findAll();
         * List<String> userEmailCollect = checkingUser.stream()
         * .map(u -> u.getEmail())
         * .collect(Collectors.toList());
         * 
         * if (requestDTO.getUsername() == null) {
         * throw new Exception400("유저네임을 입력하셔야 합니다.");
         * }
         * 
         * for (String userEmail : userEmailCollect) {
         * if (userEmail.equals(requestDTO.getEmail())) {
         * throw new Exception400("유저 이메일이 동일합니다. 고유한 이메일을 입력해주세요.");
         * }
         * }
         */


        try {
            userJPARepository.save(requestDTO.toEntity());
        } catch (Exception e) {
            throw new Exception400("존재하는 이메일입니다.");
        }


    }

    public String login(UserRequest.LoginDTO requestDTO) {
        User userPS = userJPARepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new Exception400("email을 찾을 수 없습니다 : " + requestDTO.getEmail()));
        return JwtTokenUtils.create(userPS);
    }
}
