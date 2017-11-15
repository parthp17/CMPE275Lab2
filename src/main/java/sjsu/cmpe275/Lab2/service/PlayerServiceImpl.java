package sjsu.cmpe275.Lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275.Lab2.repositories.PlayerRepository;

/**
 * Created by kemy on 11/13/17.
 */

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player save(Player player) {
        return this.playerRepository.save(player);
    }

    @Override
    public Player findOne(long id) {
        return this.playerRepository.findOne(id);
    }

    @Override
    public Player update(Player player) {
        return this.playerRepository.save(player);
    }

    @Override
    public void delete(long id) {
        this.playerRepository.delete(id);
    }
}