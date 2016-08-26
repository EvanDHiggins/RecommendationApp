package com.evan.musicrecommender;

/**
 * Created by Evan on 8/8/2016.
 */
public class RecommendationEntry {

    private Optional<String> artistName;
    private Optional<String> albumName;

    RecommendationEntry(String artist, String album) {
        artistName = Optional.of(artist);
        albumName = Optional.ofNullable(album);
    }

    RecommendationEntry(String artist) {
        artistName = Optional.of(artist);
        albumName = Optional.empty();
    }

    public Optional<String> getArtist() {
        return artistName;
    }

    public Optional<String> getAlbum() {
        return albumName;
    }

    public boolean hasAlbum() {
        return albumName.isPresent();
    }
}
