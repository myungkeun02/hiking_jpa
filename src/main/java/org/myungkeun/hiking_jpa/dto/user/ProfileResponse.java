package org.myungkeun.hiking_jpa.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myungkeun.hiking_jpa.entities.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProfileResponse {
    private String username;
    private String email;
    private Role role;
}
