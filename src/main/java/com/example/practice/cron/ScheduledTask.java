package com.example.practice.cron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTask {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Scheduled(cron = "0 10 * * * ?" )
    public void reportCurrentTime() {
        log.info("Current time: " + sdf.format(new Date()));
    }

}
