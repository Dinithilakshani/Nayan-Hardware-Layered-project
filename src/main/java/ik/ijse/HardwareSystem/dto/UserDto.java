package ik.ijse.HardwareSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class UserDto {


    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    @ToString
    public class userDto {
        private String username;
        private String Password;

        private String Email;
    }
}
