package com.rightfindpro.become;

public interface GenericMapper<T,U> {
    U toDto(T entity);

}
