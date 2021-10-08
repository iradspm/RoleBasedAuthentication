package com.example.meetingwithroles;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordEncode {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("peter");
        System.out.println(encodedPassword);
    }
}
