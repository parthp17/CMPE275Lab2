package sjsu.cmpe275.Lab2.service;

import sjsu.cmpe275.Lab2.model.Player;

public interface PlayerService {

    Player save(Player player);
    Player findOne(long id);
    Player update(Player player);
    void delete(long id);
}
