package co.edu.uptc.proyecto.service;

import java.util.List;

import co.edu.uptc.proyecto.domain.Review;
import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.repository.ReviewRepository;

public class ReviewService {
	private ReviewRepository reviewRepository;
    
    public ReviewService() {
		super();
		this.reviewRepository = new ReviewRepository();
	}



	public boolean createReview(String id, String author, double score, String comment, String date, VideoGame videoGame) {
        Review review = new Review(Integer.parseInt(id.trim()), author.trim(), score, comment.trim(), date.trim(), videoGame);
        if (review.getVideoGame() == null) {
			return false;
		}
        videoGame.getReviews().add(review);
        reviewRepository.saveReview(review);
        return true;
    }

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(int id) {
        Review review = reviewRepository.findReviewById(id);
        return review;
    }

    public boolean updateReview(int id, String author, double score, String comment, String date, VideoGame videoGame) {
    	if (!reviewRepository.existsReview(id)) {
			return false;
		}
    	Review existing = getReviewById(id);
        reviewRepository.update(existing);
        return true;
    }

    public boolean deleteReview(int id) {
        getReviewById(id); // valida que exista
        return reviewRepository.deleteReview(id);
    }
}
