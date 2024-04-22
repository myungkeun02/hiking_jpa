package org.myungkeun.hiking_jpa.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myungkeun.hiking_jpa.entities.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private String accessToken;
    private String refreshToken;
}
