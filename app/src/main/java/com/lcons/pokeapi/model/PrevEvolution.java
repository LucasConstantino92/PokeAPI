package com.lcons.pokeapi.model;

public class PrevEvolution {

    private String num;
    private String name;

    public PrevEvolution() {
    }

    public PrevEvolution(String num, String name) {
        this.num = num;
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
