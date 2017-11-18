package sjsu.cmpe275.Lab2.service;

import java.util.List;

import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275.Lab2.model.Sponsor;

public interface PlayerService {

    Player save(Player player);
    Player findOne(long id);
    Player update(Player player);
    void delete(long id);
    public Player findBySponsor(Sponsor sponsor);
}
