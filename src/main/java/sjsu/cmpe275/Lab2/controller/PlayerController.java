package sjsu.cmpe275.Lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275.Lab2.service.PlayerService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kemy on 11/13/17.
 */

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public Player createPlayer(HttpServletRequest request)
    {
        Player player = new Player();
        player.setFirstName(request.getParameter("firstname"));
        player.setLastname(request.getParameter("lastname"));
        player.setEmail(request.getParameter("email"));
        player.setDescription(request.getParameter("description"));
//        player.setSponsor(request.getParameter("sponsor"));
        return playerService.save(player);
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET)
    public Player getPlayer(@PathVariable long id)
    {
        return playerService.findOne(id);
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.POST)
    public Player updatePlayer(@PathVariable long id, HttpServletRequest request)
    {
        Player player = new Player();
        player.setId(id);
        player.setFirstName(request.getParameter("firstname"));
        player.setLastname(request.getParameter("lastname"));
        player.setEmail(request.getParameter("email"));
        player.setDescription(request.getParameter("description"));
//        player.setSponsor(request.getParameter("sponsor"));
        return playerService.update(player);
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable long id)
    {
        playerService.delete(id);
    }
}
