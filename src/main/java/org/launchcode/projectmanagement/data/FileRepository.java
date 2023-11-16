package org.launchcode.projectmanagement.data;

import org.launchcode.projectmanagement.models.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File,Integer> {
}
