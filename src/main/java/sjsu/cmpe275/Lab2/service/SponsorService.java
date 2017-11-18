package sjsu.cmpe275.Lab2.service;

import sjsu.cmpe275.Lab2.model.Sponsor;


public interface SponsorService {
	Sponsor save(Sponsor sponsor);
	Sponsor findOne(long id);
    void delete(long id);
    boolean existsById(long id);
}