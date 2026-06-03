package co.edu.uptc.proyecto.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import co.edu.uptc.proyecto.domain.Review;
import co.edu.uptc.proyecto.domain.VideoGame;
 
public class ReviewRepository {
 
    private static Map<Integer, Review> reviews = new HashMap<>();
 
    public Review saveReview(Review review) {
        reviews.put(review.getId(), review);
        return review;
    }
 
    public Review findReviewById(int id) {
        return reviews.get(id);
    }
 
    public List<Review> findAll() {
        return new ArrayList<Review>(reviews.values());
    }
 
   
 
    public Review update(Review review) {
        if (!reviews.containsKey(review.getId())) {
            return null;
        }
        reviews.put(review.getId(), review);
        return review;
    }
 
    public boolean deleteReview(int id) {
        if (!reviews.containsKey(id)) {
            return false;
        }
        reviews.remove(id);
        return true;
    }
 
    // Composición: eliminar todas las reviews de un videojuego
    public void deleteByVideoGameId(VideoGame videoGame) {
        reviews.entrySet().removeIf(e -> e.getValue().getVideoGame() == videoGame);
    }
 
    public boolean existsReview(int id) {
        return reviews.containsKey(id);
    }

	public Map<Integer, Review> getReviews() {
		return reviews;
	}

	public void setReviews(Map<Integer, Review> reviews) {
		this.reviews = reviews;
	}
    
    
}
 
