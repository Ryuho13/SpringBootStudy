package com.winter.app.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map; // 추가

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User; // 추가

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "username")
// UserDetails: 일반 로그인용, OAuth2User: 소셜 로그인용
public class UserDTO implements UserDetails, OAuth2User {

    @NotBlank(groups = {RegisterGroup.class})
    private String username;
    
    // ... (기존 필드들은 변경 없음)

    @NotBlank(groups = {RegisterGroup.class, PasswordGroup.class})
    private String password;

    private String passwordCheck;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @NotBlank(groups = {RegisterGroup.class, UpdateGroup.class})
    private String name;

    @Email(groups = {RegisterGroup.class, UpdateGroup.class})
    @NotBlank(groups = {RegisterGroup.class})
    private String email;

    @Pattern(regexp = "^01(?:0|1|[6-9])-[0-9]{3,4}-[0-9]{4}$", 
             groups = {RegisterGroup.class, UpdateGroup.class})
    private String phone;

    @Past(groups = {RegisterGroup.class, UpdateGroup.class})
    private LocalDate birth;

    private UserFileDTO userFileDTO;

    private List<RoleDTO> roleDTOs;

    // OAuth2 사용자를 위한 추가 필드
    private Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roleDTOs != null) {
            for (RoleDTO dto : roleDTOs) {
                authorities.add(new SimpleGrantedAuthority(dto.getRoleName()));
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // -- OAuth2User 인터페이스의 필수 메서드 구현 --
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getName() {
        // 여기서는 username을 고유 식별자로 사용
        return this.username;
    }
}
