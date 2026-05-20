package co.edu.uptc.proyecto.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import co.edu.uptc.proyecto.domain.VideoGame;

public class VideoGameRepository {

    private Map<Integer, VideoGame> videoGames = new HashMap<>();
    private int nextId = 1;
   

    public VideoGame save(VideoGame videoGame) {
        videoGame.setId(nextId++);
        videoGames.put(videoGame.getId(), videoGame);
        return videoGame;
    }

    public VideoGame findById(int id) {
        return videoGames.get(id);
    }

    public Collection<VideoGame> findAll() {
        return videoGames.values();
    }

    public VideoGame update(VideoGame videoGame) {
        if (!videoGames.containsKey(videoGame.getId())) {
            return null;
        }
        videoGames.put(videoGame.getId(), videoGame);
        return videoGame;
    }

    public boolean delete(int id) {
        if (!videoGames.containsKey(id)) {
            return false;
        }
        videoGames.remove(id);
        return true;
    }

    public boolean exists(int id) {
        return videoGames.containsKey(id);
    }
}