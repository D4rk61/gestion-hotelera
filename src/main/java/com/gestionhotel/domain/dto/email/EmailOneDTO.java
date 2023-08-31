package com.gestionhotel.domain.dto.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Getter
@Setter
@NoArgsConstructor  @AllArgsConstructor
public class EmailOneDTO {

    private String toUser;
    private String subject;
    private String message;
}
