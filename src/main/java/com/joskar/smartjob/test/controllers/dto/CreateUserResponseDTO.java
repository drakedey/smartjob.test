package com.joskar.smartjob.test.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joskar.smartjob.test.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserResponseDTO {
    private UUID id;
    private Date created;
    private Date modified;
    @JsonProperty("last_login")
    private Date lastLogin;
    private String token;
    private Boolean isActive;

    public CreateUserResponseDTO(User user) {
        this.id = user.getId();
        this.created = user.getCreated();
        this.modified = user.getUpdated();
        this.lastLogin = user.getLastLogin();
        this.token = user.getToken();
        this.isActive = user.getActive();
    }

}
