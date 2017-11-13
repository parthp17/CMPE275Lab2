package sjsu.cmpe275.Lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sjsu.cmpe275.Lab2.model.Test;
import sjsu.cmpe275.Lab2.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	public Test createTest()
	{
		Test t = new Test();
        t.setTestValue(1);
        return testService.save(t);
	}
}
