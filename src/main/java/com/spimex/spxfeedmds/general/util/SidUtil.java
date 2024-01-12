package com.spimex.spxfeedmds.general.util;


import com.spimex.spxfeedmds.general.configuration.SpimexProperties;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class SidUtil {

    private final SpimexProperties spimexProperties;

    public boolean isStringInMinecSidList(String value) {
        return spimexProperties.getMinec().contains(value);
    }

    public boolean isCbrfSidPostfix(String sid) {
        for (String sidPostfix : spimexProperties.getCbrf()) {
            if (sid != null && sid.endsWith(sidPostfix)) {
                return true;
            }
        }
        return false;
    }

}
