package com.winter.app.users;

import com.winter.app.files.FileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserFileDTO extends FileDTO {
    private String username;
}
