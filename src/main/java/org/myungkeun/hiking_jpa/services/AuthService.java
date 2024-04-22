package org.myungkeun.hiking_jpa.services;


import org.myungkeun.hiking_jpa.dto.api.ApiResponseDto;
import org.myungkeun.hiking_jpa.dto.auth.LoginRequest;
import org.myungkeun.hiking_jpa.dto.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ApiResponseDto<?>> registerUser(RegisterRequest request);
    ResponseEntity<ApiResponseDto<?>> loginUser(LoginRequest request);
}
