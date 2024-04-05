package com.jakuboc.TennisCourtSystem.Mappers.Impl;

import com.jakuboc.TennisCourtSystem.Mappers.Mapper;
import com.jakuboc.TennisCourtSystem.domain.dto.CourtDto;
import com.jakuboc.TennisCourtSystem.domain.entities.Court;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CourtMapperImpl implements Mapper<Court, CourtDto> {
    private final ModelMapper modelMapper;

    public CourtMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        //
        this.modelMapper
                .createTypeMap(Court.class, CourtDto.class)
                .addMapping(src -> src.getSurfaceType().getId(),
                                CourtDto::setSurfaceId);
    }

    @Override
    public CourtDto mapTo(Court court) {
        return modelMapper.map(court, CourtDto.class);
    }

    @Override
    public Court mapFrom(CourtDto courtDto) {
        return modelMapper.map(courtDto, Court.class);
    }
}
