package com.moloko.consolecrudapp.repository;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface GenericRepository<T> {
    List<T> getAll();
    void deleteFromId(int id);
}
