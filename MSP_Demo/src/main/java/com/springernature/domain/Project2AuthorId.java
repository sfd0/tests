/**
 *
 */
package com.springernature.domain;

import java.io.Serializable;

import lombok.Data;

/**
 *
 */
@Data
public class Project2AuthorId implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long projectId;

    private long authorId;

    @Override
    public int hashCode() {
        return (int) (projectId + authorId);
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof Project2AuthorId) {
            final Project2AuthorId otherId = (Project2AuthorId) object;
            return (otherId.projectId == this.projectId) && (otherId.authorId == this.authorId);
        }
        return false;
    }

}