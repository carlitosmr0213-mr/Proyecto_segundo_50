package co.edu.uptc.proyecto.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import co.edu.uptc.proyecto.domain.VideoGame;

public class VideoGameRepository {

    private Map<Integer, VideoGame> videoGames = new HashMap<>();
    private int nextId = 1;
   

    public VideoGame saveVideoGame(VideoGame videoGame) {
        videoGame.setId(nextId++);
        videoGames.put(videoGame.getId(), videoGame);
        return videoGame;
    }

    public VideoGame findVideoGameById(int id) {
        return videoGames.get(id);
    }

    public List<VideoGame> findAll() {
        return new ArrayList<VideoGame>(videoGames.values());
    }

    public VideoGame updateVideoGame(VideoGame videoGame) {
        if (!videoGames.containsKey(videoGame.getId())) {
            return null;
        }
        videoGames.put(videoGame.getId(), videoGame);
        return videoGame;
    }

    public boolean deleteVideoGame(int id) {
        if (!videoGames.containsKey(id)) {
            return false;
        }
        videoGames.remove(id);
        return true;
    }

    public boolean existsVideoGame(int id) {
        return videoGames.containsKey(id);
    }
}