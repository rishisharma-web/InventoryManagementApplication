//package com.tcs.inventory.controller;
//
//import com.tcs.inventory.config.JwtUtil;
//import com.tcs.inventory.dto.AuthRequest;
//import com.tcs.inventory.dto.AuthResponse;
//import com.tcs.inventory.dto.SignupRequest;
//import com.tcs.inventory.entity.User;
//import com.tcs.inventory.repository.UserRepository;
//import com.tcs.inventory.service.UserService;
//
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/public/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final UserService userService;
//    private final AuthenticationManager authManager;
//    private final JwtUtil jwtUtil;
//    private final UserRepository userRepository;
//
//    @PostMapping("/signup")
//    public String register(@RequestBody SignupRequest request) {
//        userService.registerCustomer(request);
//        return "Customer registered successfully!";
//    }
//
//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody AuthRequest request) {
//        Authentication auth = authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//
//        User user = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
//
//        return new AuthResponse(token);
//    }
//}

package com.tcs.inventory.controller;

import com.tcs.inventory.dto.AuthRequest;
import com.tcs.inventory.dto.AuthResponse;
import com.tcs.inventory.dto.SignupRequest;
import com.tcs.inventory.entity.User;
import com.tcs.inventory.repository.UserRepository;
import com.tcs.inventory.service.JwtService;
import com.tcs.inventory.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public String register(@RequestBody SignupRequest request) {
        userService.registerCustomer(request);
        return "Customer registered successfully!";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}
