/**
 *
 */
package com.springernature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springernature.domain.ProjectEntity;

/**
 *
 */
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    /**
     * Get an entity by primary key.
     *
     * @param id
     *            -- primary key.
     * @return -- The entity.
     */
    ProjectEntity findById(long id);

}
