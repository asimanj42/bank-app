package az.ingress.bankapp.mapper.user;

import az.ingress.bankapp.models.dto.user.UserRequest;
import az.ingress.bankapp.models.dto.user.UserResponse;
import az.ingress.bankapp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userRequestToEntity(UserRequest userRequest) {
        return User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();
    }

    public UserResponse userEntityToResponse(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
