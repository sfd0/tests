/**
 *
 */
package com.springernature.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

/**
 * @author Wim
 */
@Data
@Entity
@Table(name = "author")
public class AuthorEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private long authorId;

    @Column(name = "title_code", length = 256)
    private String titleCode;

    @Autowired
    @Column(name = "first_name", length = 256)
    private String firstName;

    @Autowired
    @Column(name = "last_name", length = 256)
    private String lastName;

    @Autowired
    @Column(name = "email", length = 256)
    private String email;

    @Autowired
    @Column(name = "emailTemp", length = 256)
    private String emailTemp;

    @Column(name = "affiliation_institution", length = 256)
    private String affiliation;

    @OneToMany(mappedBy = "author")
    private List<Project2Author> projects;

}
