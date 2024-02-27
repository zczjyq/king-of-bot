package com.kob.backend.service.user.bot;

import ch.qos.logback.core.pattern.color.BoldYellowCompositeConverter;
import com.kob.backend.pojo.Bot;

import java.util.List;

public interface GetListService {
    List<Bot> getlist();
}
