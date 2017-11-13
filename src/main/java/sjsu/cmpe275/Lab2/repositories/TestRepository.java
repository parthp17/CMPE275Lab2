package sjsu.cmpe275.Lab2.repositories;

import org.springframework.data.repository.CrudRepository;

import sjsu.cmpe275.Lab2.model.Test;

public interface TestRepository extends CrudRepository<Test, Integer>{
	
}
