/**
 * 
 */
package sjsu.cmpe275.Lab2.repositories;

import org.springframework.data.repository.CrudRepository;

import sjsu.cmpe275.Lab2.model.Sponsor;

/**
 * @author rahil
 *
 */
public interface SponsorRepository extends CrudRepository<Sponsor, Long> {
}
