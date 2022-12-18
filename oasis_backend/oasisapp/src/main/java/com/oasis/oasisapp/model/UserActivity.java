package com.oasis.oasisapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_login")
    private User user;

    private String activity_text;

    private Timestamp send_date;

    public UserActivity(User user, String activity_text, Timestamp send_date) {
        this.user = user;
        this.activity_text = activity_text;
        this.send_date = send_date;
    }

    public UserActivity() {
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

    public String getActivity_text() {
        return activity_text;
    }

    public void setActivity_text(String activity_text) {
        this.activity_text = activity_text;
    }

    public Timestamp getSend_date() {
        return send_date;
    }

    public void setSend_date(Timestamp send_date) {
        this.send_date = send_date;
    }
}
