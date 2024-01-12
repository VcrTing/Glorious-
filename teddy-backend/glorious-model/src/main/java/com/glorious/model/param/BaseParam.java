package com.glorious.model.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class BaseParam implements Serializable {

    private String sort;
    private Short page;
    private Short size;
    private String search;

    public Boolean hasSort() {
        return sort != null && (!sort.trim().isEmpty());
    }
    public Boolean isAsc() {
        return hasSort() && sort.toUpperCase().contains("ASC");
    }
    public Boolean hasSearch() {
        return search != null && (!search.trim().isEmpty());
    }

}
