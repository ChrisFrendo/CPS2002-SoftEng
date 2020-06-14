package team;

import player.Player;
import player.Position;

/**
 * Class used to represent a team of players
 * Extends Subject so it can have a subject - observer relationship between the players in the team
 * The state of the Team holds the most recent position from any player in the team
 */
public class Team extends Subject<Position> {

    /**
     * Stores the index of the current player from the list of Observers (players)
     */
    private int currentPlayer = 0;

    /**
     * Stores the team's id (name)
     */
    private String teamId;

    public Team(String teamId) {
        this.teamId = teamId;
    }

    /**
     * Increments the index of currentPlayer
     * If the index will go out of bounds the modulo shifts it back to 0
     */
    public void incrementCurrentPlayerCount() {
        this.currentPlayer = (this.currentPlayer + 1) % this.observers.size();
    }

    /**
     * Returns the player who is to play next
     *
     * @return The player at index currentPlayer from observers
     */
    public Player getCurrentPlayer() {
        return (Player) this.observers.get(currentPlayer);
    }

    /**
     * @return The team's id
     */
    public String getTeamId() {
        return teamId;
    }

}
