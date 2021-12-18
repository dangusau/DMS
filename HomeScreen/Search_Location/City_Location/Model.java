package com.dinosoftlabs.uber.HomeScreen.Search_Location.City_Location;

public class Model {

    String title;
    Integer type;

    public Model(String name, Integer type) {
        this.title = name;
        this.type = type;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
