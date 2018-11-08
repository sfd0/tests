package com.springernature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @param <T>
 *            --
 */
public interface Repository<T> extends JpaRepository<T, Long> {

    /**
     * Get an entity by primary key.
     *
     * @param id
     *            -- primary key.
     * @return -- The entity.
     */
    T findById(long id);

}
