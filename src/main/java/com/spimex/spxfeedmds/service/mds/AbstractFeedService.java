package com.spimex.spxfeedmds.service.mds;

import com.spimex.spxfeedmds.general.dto.FeedContributeRequest;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public abstract class AbstractFeedService {

    protected abstract FeedResponse addValues(FeedContributeRequest requests);
}
