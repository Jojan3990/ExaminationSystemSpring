package com.rightfindpro.become;

import lombok.Data;

import java.util.List;
@Data
public class PageDto <T> {
 int totalPages;
 int currentPage;
 int totalElements;
 List<T> data;

}
