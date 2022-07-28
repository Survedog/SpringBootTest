package com.example.test2.controller;

import com.example.test2.dto.PersonDTO;
import com.example.test2.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping("/save")
    public String save(@RequestBody PersonDTO dto) {
        testService.save(dto);
        return "save Success";
    }

    @PostMapping("/saveAll")
    public String saveAll(@RequestBody PersonDTO[] dto) {
        testService.saveAll(dto);
        return "saveAll Success";
    }

    @GetMapping("/find")
    public PersonDTO find(@RequestBody String name) {
        return testService.find(name);
    }

    @GetMapping("/findAll")
    public List<PersonDTO> findAll() {
        return testService.findAll();
    }

    @GetMapping("/get")
    @ResponseBody
    public String getFixedData() {
        return " 고정 데이터";
    }
}


