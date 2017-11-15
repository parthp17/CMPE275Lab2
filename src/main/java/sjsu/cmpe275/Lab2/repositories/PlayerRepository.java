package sjsu.cmpe275.Lab2.repositories;

import org.springframework.data.repository.CrudRepository;
import sjsu.cmpe275.Lab2.model.Player;

/**
 * Created by kemy on 11/13/17.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
