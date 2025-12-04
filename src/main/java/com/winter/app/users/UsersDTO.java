package com.winter.app.users;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersDTO {

    // Define Validation Groups
    public interface RegisterGroup {}
    public interface UpdateGroup {} // New Update Group

    @Pattern(regexp = "^(?=(?:.*\\d){2,})(?=.*[a-zA-Z]).{5,}$",
             groups = {RegisterGroup.class}, // Only validate on registration
             message = "아이디는 영어 5글자 이상이며 숫자 2개 이상을 포함해야 합니다.")
    private String username;

    @Pattern(groups = {RegisterGroup.class, UpdateGroup.class}, // Validate on both register and update
            regexp = "^(?=(?:.*\\d){2,})(?=(?:.*[a-zA-Z]){5,})(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,20}$",
            message = "비밀번호는 영어 5글자 이상, 숫자 2개 이상, 특수문자 1개 이상을 포함한 8~20자로 입력하세요.")
    private String password;
    
    private String passwordCheck; // Password confirmation, typically handled in service/controller
    private String idCheck;       // Username availability check flag, not a direct DTO property for validation

    @NotBlank(message = "이름을 기입하여 주세요.",
            groups = {RegisterGroup.class, UpdateGroup.class}) // Validate on both
    private String name;

    @NotBlank(message = "이메일을 기입하여 주세요.",
              groups = {RegisterGroup.class, UpdateGroup.class}) // Validate on both
    @Email (message ="이메일의 형식에 맞지 않습니다.",
            groups = {RegisterGroup.class, UpdateGroup.class}) // Validate on both
    private String email;

    @Pattern(regexp = "^01[016789]-?[0-9]{3,4}-?[0-9]{4}$",
             message = "휴대폰 번호 형식이 올바르지 않습니다.",
             groups = {RegisterGroup.class, UpdateGroup.class}) // Validate on both
    private String phone;

    @NotNull(message = "생년월일은 필수 입력입니다.",
            groups = {RegisterGroup.class, UpdateGroup.class}) // Validate on both
    @Past(message = "생년월일은 과거 날짜여야 합니다.",
          groups = {RegisterGroup.class, UpdateGroup.class}) // Validate on both
    private LocalDate birth;

    private List<UsersFileDTO> fileDTOs;
}
