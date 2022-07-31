package com.rightfindpro.become.mapping;

import com.rightfindpro.become.domain.User;
import com.rightfindpro.become.dto.PageDto;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Map;

public class PageDtoMapper<T> {
    public PageDto toPageResponse (Page page) {

        PageDto<T> pageDto = new PageDto<>();


        List<T> data = page.getContent();
        pageDto.setCurrentPage(page.getNumber());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setTotalElements(page.getNumberOfElements());
        pageDto.setData(data);

        return pageDto;

       // return new ResponseEntity<>(response, HttpStatus.OK);





    }

}
