package az.ingress.bankapp.controller;

import az.ingress.bankapp.models.dto.pagination.PageResponse;
import az.ingress.bankapp.models.dto.user.UserRequest;
import az.ingress.bankapp.models.dto.user.UserResponse;
import az.ingress.bankapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<UserResponse> getUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@RequestBody UserRequest userRequest, @PathVariable("userId") Long userId) {
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse deleteUser(@PathVariable("userId") Long userId) {
        return userService.deleteUser(userId);
    }

}
