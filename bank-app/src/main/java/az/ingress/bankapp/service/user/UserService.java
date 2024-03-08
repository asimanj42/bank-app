package az.ingress.bankapp.service.user;

import az.ingress.bankapp.models.dto.pagination.PageResponse;
import az.ingress.bankapp.models.dto.user.UserRequest;
import az.ingress.bankapp.models.dto.user.UserResponse;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
    PageResponse<UserResponse> getAllUsers(Pageable pageable);
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UserRequest userRequest);
    UserResponse deleteUser(Long id);
}
