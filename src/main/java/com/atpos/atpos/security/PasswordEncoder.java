package com.atpos.atpos.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main (String... args){
        System.out.println(new BCryptPasswordEncoder().encode("daniel"));
        System.out.println(new BCryptPasswordEncoder().encode("cristian"));
    }
}
