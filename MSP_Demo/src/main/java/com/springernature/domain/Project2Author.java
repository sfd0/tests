/**
 *
 */
package com.springernature.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 */
@Data
@Entity
@Table(name = "project2author")
@IdClass(Project2AuthorId.class)
public class Project2Author {

    @Id
    @Column(name = "project_id")
    private long projectId;

    @Id
    @Column(name = "author_id")
    private long authorId;

    @Column(name = "sequence_number")
    private int sequenceNumber;

    @Column(name = "collaborator_type_code")
    private String collaboratorTypeCode;

    @ManyToOne
    // @PrimaryKeyJoinColumn(name = "project_id", referencedColumnName = "id")
    @JoinColumn(name = "project_id", updatable = false, insertable = false, referencedColumnName = "id")
    private ProjectEntity project;
    /*
     * the same goes here: if this JPA model doesn't create a table for the "project2author" entity,
     * please comment out the @PrimaryKeyJoinColumn, and use the ff:
     * @JoinColumn(name = "project_id", updatable = false, insertable = false) or @JoinColumn(name =
     * "project_id", updatable = false, insertable = false, referencedColumnName = "id")
     */

    @ManyToOne
    // @PrimaryKeyJoinColumn(name = "author_id", referencedColumnName = "author_id")
    @JoinColumn(name = "author_id", updatable = false, insertable = false, referencedColumnName = "author_id")
    private AuthorEntity author;
    /*
     * if this JPA model doesn't create a table for the "project2author" entity, please comment out
     * the @PrimaryKeyJoinColumn, and use the ff:
     * @JoinColumn(name = "author_id", updatable = false, insertable = false) or @JoinColumn(name =
     * "author_id", updatable = false, insertable = false, referencedColumnName = "author_id")
     */

}