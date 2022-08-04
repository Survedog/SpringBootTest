package com.example.test2.service;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.Conversation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlackNotifyService {

    @Value("${slack.token}")
    String token;

    @Value("${spring.profiles.active}")
    String springProfile;

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshedEvent(ContextRefreshedEvent event) {
        if (springProfile.equals("server")) {
            String channelId = getChannelId("slack-bot-test");
            sendMessage(channelId, LocalDateTime.now() + " - 서버를 기동합니다.");
        }
    }

    @EventListener(ContextClosedEvent.class)
    public void onContextClosedEvent(ContextClosedEvent event) {
        if (springProfile.equals("server")) {
            String channelId = getChannelId("slack-bot-test");
            sendMessage(channelId, LocalDateTime.now() + " - 서버가 종료되었습니다.");
        }
    }

    public String getChannelId(String channelName) {
        String channelId = null;
        var client = Slack.getInstance().methods();

        try {
            var result = client.conversationsList(
                    requestConfig -> requestConfig.token(token)
            );
            for (Conversation channel : result.getChannels()) {
                if (channel.getName().equals(channelName)) {
                    channelId = channel.getId();
                    log.info("Channel ID: {}", channelId);
                    break;
                }
            }
        } catch (IOException | SlackApiException e) {
            log.error("error: {}", e.getMessage(), e);
        }

        return channelId;
    }


    public void sendMessage(String channelId, String text) {
        if (channelId == null) {
            log.error("error: channelId is null");
            return;
        }

        var client = Slack.getInstance().methods();
        try {
            var result = client.chatPostMessage(
                    requestConfig -> requestConfig
                                        .token(token)
                                        .channel(channelId)
                                        .text(text)
            );
            log.info("result {}", result);
        } catch (IOException | SlackApiException e) {
            log.error("error: {}", e.getMessage(), e);
        }
    }
}
