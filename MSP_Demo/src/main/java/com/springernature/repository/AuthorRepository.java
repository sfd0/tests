package com.springernature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springernature.domain.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    /**
     * Get Proposal by author_id.
     *
     * @param authorId
     *            --
     * @return AuthorEntity
     */
    AuthorEntity findByAuthorId(long authorId);

}
