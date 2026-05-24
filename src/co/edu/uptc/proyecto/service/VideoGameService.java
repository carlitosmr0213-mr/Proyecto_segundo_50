package co.edu.uptc.proyecto.service;


import java.util.List;

import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.repository.ReviewRepository;
import co.edu.uptc.proyecto.repository.VideoGameRepository;

public class VideoGameService {
	private VideoGameRepository videoGameRepository;
    private ReviewRepository reviewRepository;

    public VideoGameService() {
	}

	public VideoGameService(VideoGameRepository videoGameRepository, ReviewRepository reviewRepository) {
        this.videoGameRepository = videoGameRepository;
        this.reviewRepository = reviewRepository;
    }

    public boolean addVideoGame(VideoGame videoGame) {
        if (videoGame.getPrice() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (videoGame.getReleaseYear() < 1970 || videoGame.getReleaseYear() > 2030) {
            throw new IllegalArgumentException("El año de lanzamiento no es válido.");
        }
        videoGameRepository.saveVideoGame(videoGame);
        return true;
    }

    public List<VideoGame> getAll() {
        return videoGameRepository.findAll();
    }

    public VideoGame getVideoGameById(int id) {
        VideoGame vg = videoGameRepository.findVideoGameById(id);
        if (vg == null) {
            throw new IllegalArgumentException("No existe un videojuego con ID: " + id);
        }
        return vg;
    }

    public boolean updateVideoGame(VideoGame videoGame) {
        VideoGame existing = getVideoGameById(videoGame.getId());
        if (videoGame.getTitle() != null && !videoGame.getTitle().trim().isEmpty()) existing.setTitle(videoGame.getTitle().trim());
        if (videoGame.getGenre() != null && !videoGame.getGenre().trim().isEmpty()) existing.setGenre(videoGame.getGenre().trim());
        if (videoGame.getReleaseYear() >= 1970 && videoGame.getReleaseYear()<= 2030) existing.setReleaseYear(videoGame.getReleaseYear());
        if (videoGame.getPrice() >= 0) existing.setPrice(videoGame.getPrice());
        if (videoGame.getPlataform() != null && !videoGame.getPlataform().trim().isEmpty()) existing.setPlatform(videoGame.getPlataform().trim());
        if(!videoGameRepository.existsVideoGame(videoGame.getId())) {
        	return false;
        }
        videoGameRepository.updateVideoGame(existing);
        return true;
    }

    // Composición: al eliminar el juego se eliminan sus reviews
    public boolean deleteVideoGame(int id) {
        getVideoGameById(id); // valida que exista
        reviewRepository.deleteByVideoGameId(id);
        return videoGameRepository.deleteVideoGame(id);
    }
}
