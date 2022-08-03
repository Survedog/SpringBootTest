package com.example.test2.controller;

import com.example.test2.dto.PersonDTO;
import com.example.test2.service.SlackNotifyService;
import com.example.test2.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestService testService;
    private final SlackNotifyService slackService;

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
        log.info("Called getFixedData()");
        return "고정 데이터";
    }

    @PostMapping("/error")
    public String throwError() {
        testService.throwError();
        return "error thrown.";
    }

    @PostMapping("/notify")
    public String notifyOnSlack() {
        String channelId = slackService.getChannelId("slack-bot-test");
        slackService.sendMessage(channelId, "메시지 전송 테스트");
        return "notification complete.";
    }
}


