package com.spimex.spxfeedmds.controller.feing;

import com.spimex.spxfeedmds.general.dto.BasketOpec;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Feign клиент вызова архивов OPEC в xml формате и конвертации в ДТО.
 *
 * @author Gleb Chernyakov
 */
@FeignClient(value = "OpecBasket", url = "${feign.url-client}")
public interface OpecBasketFeignClient {

    @GetMapping("/basket/basketDayArchives.xml")
    @Headers("Accept: text/xml")
    List<BasketOpec> callOpec();
}
