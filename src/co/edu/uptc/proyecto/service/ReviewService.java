package co.edu.uptc.proyecto.service;

import java.util.List;

import co.edu.uptc.proyecto.domain.Review;
import co.edu.uptc.proyecto.repository.ReviewRepository;
import co.edu.uptc.proyecto.repository.VideoGameRepository;

public class ReviewService {
	private ReviewRepository reviewRepository;
    private VideoGameRepository videoGameRepository;

    public ReviewService(ReviewRepository reviewRepository, VideoGameRepository videoGameRepository) {
        this.reviewRepository = new ReviewRepository();
        this.videoGameRepository = new VideoGameRepository();
    }

    
    
    public ReviewService() {
		super();
		this.reviewRepository = new ReviewRepository();
        this.videoGameRepository = new VideoGameRepository();
	}



	public boolean createReview(String id, String author, double score, String comment, String date, int videoGameId) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }
        if (score < 0.0 || score > 10.0) {
            throw new IllegalArgumentException("La puntuación debe estar entre 0.0 y 10.0.");
        }
        if (videoGameRepository.findVideoGameById(videoGameId) == null) {
            throw new IllegalArgumentException("No existe un videojuego con ID: " + videoGameId);
        }
        Review review = new Review(0, author.trim(), score, comment.trim(), date.trim(), videoGameId);
        reviewRepository.saveReview(review);
        return true;
    }

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(int id) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new IllegalArgumentException("No existe una reseña con ID: " + id);
        }
        return review;
    }

    public List<Review> getByVideoGameId(int videoGameId) {
        if (videoGameRepository.findVideoGameById(videoGameId) == null) {
            throw new IllegalArgumentException("No existe un videojuego con ID: " + videoGameId);
        }
        return reviewRepository.findByVideoGameId(videoGameId);
    }

    public boolean updateReview(int id, String author, double score, String comment, String date) {
        Review existing = getReviewById(id);
        if (author != null && !author.trim().isEmpty()) existing.setAuthor(author.trim());
        if (score >= 0.0 && score <= 10.0) existing.setScore(score);
        if (comment != null && !comment.trim().isEmpty()) existing.setComment(comment.trim());
        if (date != null && !date.trim().isEmpty()) existing.setDate(date.trim());
        if (!reviewRepository.existsReview(id)) {
			return false;
		}
        reviewRepository.update(existing);
        return true;
    }

    public boolean deleteReview(int id) {
        getReviewById(id); // valida que exista
        return reviewRepository.deleteReview(id);
    }
}
