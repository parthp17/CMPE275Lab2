package sjsu.cmpe275.Lab2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275.Lab2.model.Sponsor;

/**
 * Created by kemy on 11/13/17.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
	
	public Player findBySponsor(Sponsor sponsor);
	public Player findByEmail(String email);
}
