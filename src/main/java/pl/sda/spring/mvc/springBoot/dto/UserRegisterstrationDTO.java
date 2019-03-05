package pl.sda.spring.mvc.springBoot.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserRegisterstrationDTO {
    private long id;
    private String login;
    private String password;
    private String repeatedPassword;
}
