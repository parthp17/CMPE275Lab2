/**
 * 
 */
package sjsu.cmpe275.Lab2.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sjsu.cmpe275.Lab2.model.Sponsor;
import sjsu.cmpe275.Lab2.repositories.SponsorRepository;

/**
 * @author rahil
 *
 */

@Service
@Transactional
public class SponsorServiceImpl implements SponsorService {

	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Override
	
	public Sponsor save(Sponsor sponsor) {
		return this.sponsorRepository.save(sponsor);
	}

	@Override
	public Sponsor findOne(long id) {
		return this.sponsorRepository.findOne(id);
	}

	
	@Override
	public void delete(long id) {
		this.sponsorRepository.delete(id);
	}

	@Override
	public boolean existsById(long id) {
		// TODO Auto-generated method stub
		return this.sponsorRepository.exists(id);
	}

}
