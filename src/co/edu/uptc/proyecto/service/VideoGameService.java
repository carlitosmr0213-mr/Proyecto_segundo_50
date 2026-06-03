package co.edu.uptc.proyecto.service;


import java.util.List;

import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.repository.ReviewRepository;
import co.edu.uptc.proyecto.repository.VideoGameRepository;

public class VideoGameService {
	private VideoGameRepository videoGameRepository;
    private ReviewRepository reviewRepository;

	public VideoGameService() {
        this.videoGameRepository = new VideoGameRepository();
        this.reviewRepository = new ReviewRepository();
    }

    public boolean addVideoGame(VideoGame videoGame) {
        videoGameRepository.saveVideoGame(videoGame);
        return true;
    }

    public List<VideoGame> getAll() {
        return videoGameRepository.findAll();
    }

    public VideoGame getVideoGameById(int id) {
        VideoGame vg = videoGameRepository.findVideoGameById(id);
        return vg;
    }

    public boolean updateVideoGame(VideoGame videoGame) {
    	 if(!videoGameRepository.existsVideoGame(videoGame.getId())) {
         	return false;
         }
        VideoGame existing = getVideoGameById(videoGame.getId());
        
        videoGameRepository.updateVideoGame(existing);
        return true;
    }

    // Composición: al eliminar el juego se eliminan sus reviews
    public boolean deleteVideoGame(int id) {
        getVideoGameById(id); // valida que exista
        reviewRepository.deleteByVideoGameId(videoGameRepository.findVideoGameById(id));
        return videoGameRepository.deleteVideoGame(id);
    }
}
