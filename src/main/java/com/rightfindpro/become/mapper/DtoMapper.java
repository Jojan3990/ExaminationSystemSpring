package com.rightfindpro.become.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DtoMapper {
    private ModelMapper modelMapper;

    public <T, S> S converToEntity(T data, Class<S> type) {
        return modelMapper.map(data, type);
    }
    public <T, S> S toDto(T data, Class<S> type) {
        return modelMapper.map(data, type);
    }

    public <T,S> List<S> convertToDtoList(List<T> lists, Class<S> type) {
        return lists.stream()
                .map(list -> toDto(list, type))
                .collect(Collectors.toList());
    }

   //Header response (page element length )

   // or

    //use pageDtoMapper in here,



  /*  public T to (U entiy);
    public U from (T dto);*/
}
