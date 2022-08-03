package com.example.test2.controller;

import com.example.test2.dto.PersonDTO;
import com.example.test2.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestService testService;

    @PostMapping("/save")
    public String save(@RequestBody PersonDTO dto) {
        log.info("Called save()");
        testService.save(dto);
        return "save Success";
    }

    @PostMapping("/saveAll")
    public String saveAll(@RequestBody PersonDTO[] dto) {
        log.info("Called saveAll()");
        testService.saveAll(dto);
        return "saveAll Success";
    }

    @GetMapping("/find")
    public PersonDTO find(@RequestBody String name) {
        log.info("Called find()");
        return testService.find(name);
    }

    @GetMapping("/findAll")
    public List<PersonDTO> findAll() {
        log.info("Called findAll()");
        return testService.findAll();
    }

    @GetMapping("/get")
    @ResponseBody
    public String getFixedData() {
        log.info("Called get()");
        return " 고정 데이터";
    }

    @GetMapping("/error")
    public void throwError() {
        testService.throwError();
    }
}


