package org.launchcode.projectmanagement.data;

import org.launchcode.projectmanagement.models.Notes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends CrudRepository<Notes,Integer> {
}
