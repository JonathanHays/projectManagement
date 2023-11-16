package org.launchcode.projectmanagement.data;

import org.launchcode.projectmanagement.models.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CrudRepository<Feature,Integer> {
}
