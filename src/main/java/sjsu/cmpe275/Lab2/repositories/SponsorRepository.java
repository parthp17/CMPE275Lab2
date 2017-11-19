/**
 * 
 */
package sjsu.cmpe275.Lab2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import sjsu.cmpe275.Lab2.model.Sponsor;

/**
 * @author rahil
 *
 */
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}
