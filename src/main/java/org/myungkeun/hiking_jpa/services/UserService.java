package org.myungkeun.hiking_jpa.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myungkeun.hiking_jpa.dto.api.ApiResponseDto;
import org.myungkeun.hiking_jpa.dto.user.UpdatePasswordRequest;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {
    ResponseEntity<ApiResponseDto<?>> getProfileByToken(Principal connectedUser);

    ResponseEntity<ApiResponseDto<?>> updatePasswordByToken(Principal connectedUser, UpdatePasswordRequest request);

    ResponseEntity<ApiResponseDto<?>> get(HttpServletRequest request, HttpServletResponse response);
}
