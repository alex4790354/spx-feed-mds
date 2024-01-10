package com.spimex.spxfeedmds.controller;

import com.spimex.spxfeedmds.general.dto.FeedContributeRequest;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import com.spimex.spxfeedmds.service.mds.FeedContributeService;
import com.spimex.spxfeedmds.service.mds.OpecBasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FeedMdsController {

    private final FeedContributeService service;
    private final OpecBasketService opecBasketService;

    @PostMapping("/contribute/SPIMEX_MDS")
    public ResponseEntity<List<FeedResponse>> contributeMdsValues(@RequestBody List<FeedContributeRequest> requests,
                                                                  @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(service.addFeedValues(requests, jwt));
    }

    @PostMapping("/contribute/SPIMEX_MDS2")
    public ResponseEntity<List<FeedResponse>> contributeMdsValues2(@RequestBody List<FeedContributeRequest> requests) {
        return ResponseEntity.ok(service.addFeedValues(requests));
    }

    @PostMapping("/manual/basket/basketDayArchives")
    public ResponseEntity<FeedResponse> getBasketDayArchives() {
        return ResponseEntity.ok(opecBasketService.getOpecArchivesJob());
    }
}
