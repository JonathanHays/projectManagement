package org.launchcode.projectmanagement.data;

import org.launchcode.projectmanagement.models.Project;
import org.launchcode.projectmanagement.models.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Integer> {

    List<Project> findByNameContainsIgnoreCaseOrderByName(String searchTerm);

    List<Project> findByStatusOrderByName(Status status);

    Object findAll(Sort sort);
}
