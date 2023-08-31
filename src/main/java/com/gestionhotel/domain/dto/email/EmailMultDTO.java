package com.gestionhotel.domain.dto.email;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailMultDTO {

    private String[] toUser;
    private String subject;
    private String message;
}