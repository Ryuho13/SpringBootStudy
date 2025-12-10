package com.winter.app.users;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("=========== OAuth2 Login 시도 =============");
        
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("카카오가 보내준 전체 데이터: {}", oAuth2User.getAttributes());
        
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("Registration ID: {}", registrationId);

        UserDTO userDTO = this.getUser(oAuth2User, registrationId);
        
        return userDTO;
    }

    private UserDTO getUser(OAuth2User oAuth2User, String registrationId) {
        UserDTO userDTO = null;
        if (registrationId.equalsIgnoreCase("kakao")) {
            userDTO = getKakaoUser(oAuth2User);
        } else {
            // 다른 소셜 로그인 처리
        }
        
        try {
            UserDTO existingUser = userDAO.detail(userDTO);

            if (existingUser != null) {
                log.info("기존 회원으로 확인됨: {}", existingUser.getUsername());

                // 최신 닉네임과 DB의 이름이 다른지 확인하고 업데이트
                String newNickname = userDTO.getName();
                if (newNickname != null && !newNickname.equals(existingUser.getName())) {
                    log.info("닉네임 변경 감지. DB 업데이트: {} -> {}", existingUser.getName(), newNickname);
                    existingUser.setName(newNickname);
                    userDAO.update(existingUser);
                }
                
                return existingUser;
            }
            
            log.info("신규 회원으로 확인됨. 회원가입을 진행합니다: {}", userDTO.getUsername());
            
            userDTO.setPassword(passwordEncoder.encode("social_password"));
            userDTO.setAccountNonExpired(true);
            userDTO.setAccountNonLocked(true);
            userDTO.setCredentialsNonExpired(true);
            userDTO.setEnabled(true);
            
            // 닉네임이 없으면 '카카오회원'으로 기본 설정
            if (userDTO.getName() == null || userDTO.getName().isEmpty()) {
                userDTO.setName("카카오회원_" + userDTO.getUsername().substring(0, 4));
                log.info("닉네임이 없어 기본 이름 설정: {}", userDTO.getName());
            }
            userDTO.setPhone("000-0000-0000");
            userDTO.setBirth(LocalDate.of(1900, 1, 1));

            userDAO.register(userDTO);
            userDAO.roleAdd(userDTO);
            
            return userDTO;
            
        } catch (Exception e) {
            log.error("DB 작업 중 오류가 발생했습니다.", e);
            throw new OAuth2AuthenticationException(new OAuth2Error("DB_ERROR", "데이터베이스 처리 중 오류가 발생했습니다.", null), e);
        }
    }

    private UserDTO getKakaoUser(OAuth2User oAuth2User) {
        // 카카오가 보내준 전체 데이터에서 'properties' 맵 추출 (닉네임이 여기에 있을 수 있음)
        Map<String, Object> properties = oAuth2User.getAttribute("properties");
        // 카카오가 보내준 전체 데이터에서 'kakao_account' 맵 추출
        Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
        // kakao_account 안에 있는 'profile' 맵 추출
        Map<String, Object> kakaoAccountProfile = (Map<String, Object>) kakaoAccount.get("profile");

        String kakaoId = oAuth2User.getName();
        String email = (String) kakaoAccount.get("email");
        String nickname = null;

        // --- 닉네임 추출 로직 개선 ---
        if (properties != null && properties.containsKey("nickname")) {
            nickname = (String) properties.get("nickname");
            log.info("properties에서 추출된 닉네임: {}", nickname);
        } else if (kakaoAccountProfile != null && kakaoAccountProfile.containsKey("nickname")) {
            nickname = (String) kakaoAccountProfile.get("nickname");
            log.info("kakaoAccount.profile에서 추출된 닉네임: {}", nickname);
        } else {
            log.warn("카카오 API 응답에서 닉네임을 찾을 수 없습니다.");
        }
        // -----------------------------

        if (email == null) {
            email = "kakao_" + kakaoId + "@example.com";
            log.warn("카카오 이메일 미제공으로 임시 이메일 생성: {}", email);
        }
        
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(kakaoId);
        userDTO.setEmail(email);
        userDTO.setName(nickname);
        userDTO.setAttributes(oAuth2User.getAttributes());
        
        return userDTO;
    }
}