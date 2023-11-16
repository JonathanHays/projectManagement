package org.launchcode.projectmanagement.data;

import org.launchcode.projectmanagement.models.Epic;
import org.launchcode.projectmanagement.models.Project;
import org.launchcode.projectmanagement.models.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpicRepository extends CrudRepository<Epic,Integer> {

}
