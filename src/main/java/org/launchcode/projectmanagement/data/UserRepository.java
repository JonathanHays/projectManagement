package org.launchcode.projectmanagement.data;
// <!-- 
// created by: Jonathan Hays
//  -->
import org.launchcode.projectmanagement.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername(String username);


}
