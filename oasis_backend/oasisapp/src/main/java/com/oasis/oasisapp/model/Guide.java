package com.oasis.oasisapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "guides")
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_login")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    private String guide_text;

    private Timestamp send_date;


    public Guide() {
    }

    public Guide(User user, Game game, String guide_text, Timestamp send_date) {
        this.user = user;
        this.game = game;
        this.guide_text = guide_text;
        this.send_date = send_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getGuide_text() {
        return guide_text;
    }

    public void setGuide_text(String guide_text) {
        this.guide_text = guide_text;
    }

    public Timestamp getSend_date() {
        return send_date;
    }

    public void setSend_date(Timestamp send_date) {
        this.send_date = send_date;
    }
}
