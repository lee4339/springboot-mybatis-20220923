package com.boot.mybatis20220923.controller.api;

import com.boot.mybatis20220923.domain.User;
import com.boot.mybatis20220923.dto.CMRespDto;
import com.boot.mybatis20220923.dto.SignupReqDto;
import com.boot.mybatis20220923.dto.SignupRespDto;
import com.boot.mybatis20220923.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(SignupReqDto signupReqDto) {

        log.info("{}", signupReqDto);

//        try {
//            throw new RuntimeException("강제로 예외 발생");
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "회원가입 실패", false));
//        }

        User user = signupReqDto.Entity();

        log.info("마이바티스 가기전 Entity: {}", user);
        int result = userRepository.save(user);
        log.info("마이바티스 다녀온 후 Entity: {}", user);

        SignupRespDto signupRespDto = user.toDto();
        
        if(result == 0) {
            return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "회원가입 실패", signupRespDto));
        }

        return ResponseEntity.ok(new CMRespDto<>(1, "회원가입 성공", signupRespDto));
    }

}
