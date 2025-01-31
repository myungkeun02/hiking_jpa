package org.myungkeun.hiking_jpa.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_jpa.dto.api.ApiResponseDto;
import org.myungkeun.hiking_jpa.dto.user.UpdatePasswordRequest;
import org.myungkeun.hiking_jpa.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")

public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponseDto<?>> getUserProfileById(
            Principal connectedUser
    ) {
        return userService.getProfileByToken(connectedUser);
    }

    @PutMapping("/update/password")
    public ResponseEntity<ApiResponseDto<?>> updatePasswordById(
            Principal connectedUser,
            @RequestBody UpdatePasswordRequest request
    ) {
        return userService.updatePasswordByToken(connectedUser, request);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponseDto<?>> get(
            HttpServletRequest request, HttpServletResponse response
    ) {
        return userService.get(request, response);
    }
}
