package co.edu.uptc.proyecto.service;


import java.util.List;

import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.repository.ReviewRepository;
import co.edu.uptc.proyecto.repository.VideoGameRepository;

public class VideoGameService {
	private VideoGameRepository videoGameRepository;
    private ReviewRepository reviewRepository;

    public VideoGameService(VideoGameRepository videoGameRepository, ReviewRepository reviewRepository) {
        this.videoGameRepository = videoGameRepository;
        this.reviewRepository = reviewRepository;
    }

    public VideoGame createVideoGame(String title, String genre, int releaseYear, double price, String platform) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (releaseYear < 1970 || releaseYear > 2030) {
            throw new IllegalArgumentException("El año de lanzamiento no es válido.");
        }
        VideoGame vg = new VideoGame(0, title.trim(), genre.trim(), releaseYear, price, platform.trim());
        return videoGameRepository.save(vg);
    }

    public List<VideoGame> getAll() {
        return videoGameRepository.findAll();
    }

    public VideoGame getVideoGameById(int id) {
        VideoGame vg = videoGameRepository.findById(id);
        if (vg == null) {
            throw new IllegalArgumentException("No existe un videojuego con ID: " + id);
        }
        return vg;
    }

    public VideoGame updateVideoGame(int id, String title, String genre, int releaseYear, double price, String platform) {
        VideoGame existing = getVideoGameById(id);
        if (title != null && !title.trim().isEmpty()) existing.setTitle(title.trim());
        if (genre != null && !genre.trim().isEmpty()) existing.setGenre(genre.trim());
        if (releaseYear >= 1970 && releaseYear <= 2030) existing.setReleaseYear(releaseYear);
        if (price >= 0) existing.setPrice(price);
        if (platform != null && !platform.trim().isEmpty()) existing.setPlatform(platform.trim());
        return videoGameRepository.update(existing);
    }

    // Composición: al eliminar el juego se eliminan sus reviews
    public boolean deleteVideoGame(int id) {
        getVideoGameById(id); // valida que exista
        reviewRepository.deleteByVideoGameId(id);
        return videoGameRepository.delete(id);
    }
}
