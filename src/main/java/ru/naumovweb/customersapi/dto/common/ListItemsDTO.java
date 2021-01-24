package ru.naumovweb.customersapi.dto.common;

import lombok.Data;

import java.util.List;

@Data
public class ListItemsDTO<T> {
    private List<T> items;
    private Integer pagesCount;
}
