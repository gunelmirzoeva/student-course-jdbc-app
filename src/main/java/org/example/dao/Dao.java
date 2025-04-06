package org.example.dao;

import java.util.List;

public interface Dao <T>{
    List<T> seeAll();
    void save(T t);
}
