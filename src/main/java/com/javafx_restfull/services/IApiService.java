package com.javafx_restfull.services;

import java.util.List;

public interface IApiService<T> {
    T get_by_id(int id);
    List<T> get_all();
    void add(T t);
    void delete(int id);
}
