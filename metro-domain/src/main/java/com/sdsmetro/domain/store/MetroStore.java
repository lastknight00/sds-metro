package com.sdsmetro.domain.store;

import com.sdsmetro.domain.entity.Metro;

import java.util.List;
import java.util.NoSuchElementException;

public interface MetroStore {
    //
    String create(Metro metro);
    Metro retrieve(String id) throws NoSuchElementException;
    Metro retrieveByName(String metroName);
    List<Metro> retrieveAll();
    void update(Metro metro);
    void delete(Metro metro);
    boolean existByName(String name);
}