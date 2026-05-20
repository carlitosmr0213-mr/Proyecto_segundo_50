package co.edu.uptc.proyecto.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import co.edu.uptc.proyecto.domain.Review;
 
public class ReviewRepository {
 
    private Map<Integer, Review> reviews = new HashMap<>();
    private int nextId = 1;
 
    public Review save(Review review) {
        review.setId(nextId++);
        reviews.put(review.getId(), review);
        return review;
    }
 
    public Review findById(int id) {
        return reviews.get(id);
    }
 
    public Collection<Review> findAll() {
        return reviews.values();
    }
 
    // Composición: obtener reviews por videojuego
    public List<Review> findByVideoGameId(int videoGameId) {
        return reviews.values().stream()
                .filter(r -> r.getVideoGameId() == videoGameId)
                .collect(Collectors.toList());
    }
 
    public Review update(Review review) {
        if (!reviews.containsKey(review.getId())) {
            return null;
        }
        reviews.put(review.getId(), review);
        return review;
    }
 
    public boolean delete(int id) {
        if (!reviews.containsKey(id)) {
            return false;
        }
        reviews.remove(id);
        return true;
    }
 
    // Composición: eliminar todas las reviews de un videojuego
    public void deleteByVideoGameId(int videoGameId) {
        reviews.entrySet().removeIf(e -> e.getValue().getVideoGameId() == videoGameId);
    }
 
    public boolean exists(int id) {
        return reviews.containsKey(id);
    }
}
 
