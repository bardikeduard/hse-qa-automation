package dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class User {
    String login;
    String password;
    String creditCard;
    String name;
}
