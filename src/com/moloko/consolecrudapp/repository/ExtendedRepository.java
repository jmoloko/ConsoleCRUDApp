package com.moloko.consolecrudapp.repository;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface ExtendedRepository<T, S> extends GenericRepository<T>{
    void createAndSave(T[] params, List<S> dataType);
    void updateFromId(T[] params, List<S> dataType);
}
