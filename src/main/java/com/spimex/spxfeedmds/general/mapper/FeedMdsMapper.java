package com.spimex.spxfeedmds.general.mapper;

import com.spimex.spxfeedmds.general.dto.RatesOpecDto;
import com.spimex.spxfeedmds.general.dto.StaticOpecDto;
import com.spimex.spxfeedmds.general.dto.request.SidFieldsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeedMdsMapper {

    FeedMdsMapper INSTANCE = Mappers.getMapper( FeedMdsMapper.class );

    StaticOpecDto requestToStaticOpec(SidFieldsRequest request);
    RatesOpecDto requestToRatesOpec(SidFieldsRequest request);
}
