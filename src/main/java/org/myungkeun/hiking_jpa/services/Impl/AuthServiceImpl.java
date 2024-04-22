package org.myungkeun.hiking_jpa.services.Impl;

import lombok.RequiredArgsConstructor;
import org.myungkeun.hiking_jpa.dto.api.ApiResponseDto;
import org.myungkeun.hiking_jpa.dto.api.ApiResponseStatus;
import org.myungkeun.hiking_jpa.dto.auth.LoginRequest;
import org.myungkeun.hiking_jpa.dto.auth.LoginResponse;
import org.myungkeun.hiking_jpa.dto.auth.RegisterRequest;
import org.myungkeun.hiking_jpa.entities.Role;
import org.myungkeun.hiking_jpa.entities.Token;
import org.myungkeun.hiking_jpa.entities.TokenType;
import org.myungkeun.hiking_jpa.entities.User;
import org.myungkeun.hiking_jpa.jwt.JwtService;
import org.myungkeun.hiking_jpa.repositories.TokenRepository;
import org.myungkeun.hiking_jpa.repositories.UserRepository;
import org.myungkeun.hiking_jpa.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<ApiResponseDto<?>> registerUser(RegisterRequest request) {
        try {
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                System.out.println();
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), ApiResponseStatus.FAIL.name(), "동일한 email의 유저가 이미 존재합니다."));
            }
            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(HttpStatus.CREATED.value(), ApiResponseStatus.SUCCESS.name(), request.getUsername() + "님의 회원가입이 완료되었습니다."));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.FAIL.name(), null));
        }

    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> loginUser(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(user, accessToken, refreshToken);
            LoginResponse response = LoginResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .role(user.getRole())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(HttpStatus.OK.value(), ApiResponseStatus.SUCCESS.name(), response));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.FAIL.name(), null));
        }

    }


    private void saveUserToken(User user, String accessToken, String refreshToken) {
        var token = Token.builder()
                .user(user)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

}
