package org.PasswordManager.exceptions;

import org.springframework.stereotype.Component;

@Component
public class WrongPasswordMetadataExceptions extends RuntimeException{
    public WrongPasswordMetadataExceptions() {
        super("[ERROR]: Wrong password metadata. Make sure all the constraints are respected:\n" +
            " - the website is a required field\n" +
            " - version is not a negative number\n" +
            " - length is higher that 15 and a mandatory field\n");
    }
}
