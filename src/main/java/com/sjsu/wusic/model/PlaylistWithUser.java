package com.sjsu.wusic.model;

public class PlaylistWithUser extends Playlist {

    private String username;
    private String userDisplayName;

    public String getUsername() {
        return username;
    }

    public PlaylistWithUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public PlaylistWithUser setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
        return this;
    }
}
