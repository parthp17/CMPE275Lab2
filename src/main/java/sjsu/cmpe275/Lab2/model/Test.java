package sjsu.cmpe275.Lab2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="test")
public class Test implements java.io.Serializable{

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	private int testValue = 0;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getTestValue() {
		return testValue;
	}

	public void setTestValue(int testValue) {
		this.testValue = testValue;
	}
	
}
