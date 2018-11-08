package com.springernature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springernature.domain.AuthorEntity;
import com.springernature.domain.Project2Author;
import com.springernature.domain.ProjectEntity;
import com.springernature.repository.AuthorRepository;
import com.springernature.repository.Project2AuthorRepository;
import com.springernature.repository.ProjectRepository;

@RestController
public class TestController {

    @Autowired
    AuthorRepository repository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    Project2AuthorRepository joinRepository;

    @RequestMapping(value = "/Test")
    public String displayName() {

        return "Wim Doornik ";
    }

    @RequestMapping(value = "/CreateAuthor")
    public String createAuthor(
            @RequestParam final String firstName,
            @RequestParam final String lastName) {
        final AuthorEntity author = new AuthorEntity();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        repository.save(author);
        return firstName + " " + lastName + " / " + author.getAuthorId();
    }

    @RequestMapping(value = "/CreateProject")
    public String createProject(@RequestParam final String name) {
        final ProjectEntity project = new ProjectEntity();
        project.setName(name);
        projectRepository.save(project);
        return name + " / " + project.getId();
    }

    @RequestMapping(value = "/Join")
    public String join(
            @RequestParam final long projectId,
            @RequestParam final long authorId,
            @RequestParam final int sequenceNumber,
            @RequestParam final String collaboratorTypeCode) {
        final ProjectEntity project = projectRepository.findById(projectId);
        final AuthorEntity author = repository.findByAuthorId(authorId);
        final Project2Author joinEntity = new Project2Author();
        joinEntity.setAuthor(author);
        joinEntity.setAuthorId(authorId);
        joinEntity.setProject(project);
        joinEntity.setProjectId(projectId);
        joinEntity.setSequenceNumber(sequenceNumber);
        joinEntity.setCollaboratorTypeCode(collaboratorTypeCode);
        joinRepository.save(joinEntity);
        return joinEntity.toString();
    }

}
