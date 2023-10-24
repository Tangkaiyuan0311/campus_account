package learn.jpa.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// to be wrapped and sent back
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status; // 200, 404 ...
    private String errMessage;
    private long timeStamp;
}
