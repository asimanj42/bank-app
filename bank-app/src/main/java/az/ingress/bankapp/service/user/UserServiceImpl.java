package az.ingress.bankapp.service.user;

import az.ingress.bankapp.exception.type.BaseException;
import az.ingress.bankapp.models.dto.pagination.PageResponse;
import az.ingress.bankapp.models.dto.user.UserRequest;
import az.ingress.bankapp.models.dto.user.UserResponse;
import az.ingress.bankapp.entity.User;
import az.ingress.bankapp.mapper.pagination.PageResponseMapper;
import az.ingress.bankapp.mapper.user.UserMapper;
import az.ingress.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static az.ingress.bankapp.models.enums.response.ErrorMessages.USER_ALREADY_EXISTS;
import static az.ingress.bankapp.models.enums.response.ErrorMessages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PageResponseMapper pageResponseMapper;
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        checkUsernameExisting(userRequest);
        User user = userMapper.userRequestToEntity(userRequest);
        User savedUser = userRepository.save(user);
        return getUserResponse(savedUser);
    }

    private UserResponse getUserResponse(User savedUser) {
        return userMapper.userEntityToResponse(savedUser);
    }

    private void checkUsernameExisting(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw BaseException.of(USER_ALREADY_EXISTS);
        }
    }

    @Override
    public PageResponse<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        Page<UserResponse> userResponses = mapPageEntityToPageResponse(users);
        return getCustomUserResponsePage(userResponses);
    }



    @Override
    public UserResponse getUserById(Long id) {
        User user = checkUserExistingGivenId(id);
        return getUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = checkUserExistingGivenId(id);
        User updatedUser = updateIfNotNull(user, userRequest);
        updatedUser.setId(id);
        User savedUser = userRepository.save(updatedUser);
        return getUserResponse(savedUser);
    }
    @Override
    public UserResponse deleteUser(Long id) {
        User user = checkUserExistingGivenId(id);
        userRepository.delete(user);
        return getUserResponse(user);
    }

    private Page<UserResponse> mapPageEntityToPageResponse(Page<User> users) {
        return users.map(userMapper::userEntityToResponse);
    }

    private PageResponse<UserResponse> getCustomUserResponsePage(Page<UserResponse> userResponses) {
        return pageResponseMapper.mapPageResponse(userResponses);
    }

    private User checkUserExistingGivenId(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> BaseException.of(USER_NOT_FOUND)
        );
    }

    private User updateIfNotNull(User user, UserRequest userRequest) {
        if (userRequest.getUsername() != null) {
            user.setUsername(userRequest.getUsername());
        }
        if (userRequest.getPassword() != null) {
            user.setPassword(userRequest.getPassword());
        }
        return user;
    }
}
