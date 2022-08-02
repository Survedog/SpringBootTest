package com.example.test2.controller;

import com.example.test2.dto.PersonDTO;
import com.example.test2.service.TestService;
import datadog.trace.api.Trace;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

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
    @Trace
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
}


