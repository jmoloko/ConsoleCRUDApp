package com.moloko.consolecrudapp.repository;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface GenericRepository<T, ID> {
    List<T> getAll();
    void deleteById(ID id);
    T getById(ID id);
    T save(T t);
    T update(T t);
}
