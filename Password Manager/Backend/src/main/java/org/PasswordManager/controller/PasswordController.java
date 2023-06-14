package org.PasswordManager.controller;

import org.PasswordManager.model.PasswordMetadata;
import org.PasswordManager.service.PasswordService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/password")
public class PasswordController {
    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping(value = "/addPassMeta")
    public ResponseEntity<Void> createPasswordMetadata(
        @Validated @RequestBody PasswordMetadata passwordMetadata
    ) {
        passwordService.addPasswordMetadata(passwordMetadata);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/delPassMeta")
    public ResponseEntity<Void> deletePasswordMetadata(
        @RequestParam String id
    ) {
        passwordService.removePasswordMetadata(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/getPassMetaList")
    public ResponseEntity<ArrayList<PasswordMetadata>> getPasswordMetadataList() {
        return ResponseEntity.status(200).body(passwordService.getPasswordMetadataList());
    }

    @GetMapping("/generatePassword")
    public ResponseEntity<String> generatePassword(
        @RequestParam String masterPass,
        @RequestParam String id
    ) {
        return ResponseEntity.status(201).body(new JSONObject()
            .put("passKey", passwordService.generatePassword(masterPass, id))
            .toString()
        );
    }
}
