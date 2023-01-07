package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    @GetMapping("/in")
//    @ResponseBody => RestController가 있으면 사용하지 않아도 된다.
    public String checkIn() {
        return "Check-In";
    }

    @GetMapping("/out")
//    @ResponseBody
    public String checkOut() {
        return "Check-Out";
    }

}
