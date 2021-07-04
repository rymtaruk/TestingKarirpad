package com.karirpad.apps.model.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created By reynard on 7/3/21.
 */
public class Company implements Serializable {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
