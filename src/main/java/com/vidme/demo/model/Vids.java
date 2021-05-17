package com.vidme.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "vids")
public class Vids {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String title;

    @Column
    private String vidurl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;



    public Vids() {
    }
    public Vids(Long id, String username, String title, String vidurl) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.vidurl = vidurl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVidurl() {
        return vidurl;
    }

    public void setVidurl(String vidurl) {
        this.vidurl = vidurl;
    }

    public User getUser() {
        return user;
    }



    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vids{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", vidurl='" + vidurl + '\'' +
                ", user=" + user +
                '}';
    }
}