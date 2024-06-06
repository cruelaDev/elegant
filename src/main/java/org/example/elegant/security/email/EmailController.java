package org.example.elegant.security.email;

import lombok.RequiredArgsConstructor;
import org.example.elegant.security.email.dto.EmailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/auth/verification")
    public ResponseEntity<?> getVerification(@RequestBody EmailDto email){
        String verified = emailService.verifyEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(verified);
    }


    @GetMapping("/auth/reTry")
    public ResponseEntity<?> reTryEmailVer(@RequestBody EmailDto emailDto){
        emailService.retry(emailDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}