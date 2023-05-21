package com.jwt.Model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JwtRequest {

    private String userName;
    private String password;



}
