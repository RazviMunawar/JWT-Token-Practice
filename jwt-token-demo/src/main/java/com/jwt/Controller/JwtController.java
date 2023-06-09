package com.jwt.Controller;

import com.jwt.Helper.JwtUtil;
import com.jwt.Model.JwtRequest;
import com.jwt.Model.JwtResponse;
import com.jwt.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            this
                    .authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
        }catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        //fine area
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println(token);

        //{"token": "value"}
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
