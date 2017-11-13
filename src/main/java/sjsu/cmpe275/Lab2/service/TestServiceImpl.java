package sjsu.cmpe275.Lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sjsu.cmpe275.Lab2.model.Test;
import sjsu.cmpe275.Lab2.repositories.TestRepository;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestRepository testRepository;
	
	@Override
	public Test save(Test test) {
		return this.testRepository.save(test);
	}

}
