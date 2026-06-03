package co.edu.uptc.proyecto.tests;


import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.proyecto.domain.Review;
import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.service.ReviewService;
import co.edu.uptc.proyecto.service.VideoGameService;

class ReviewServiceTest {
	private ReviewService reviewService;
	private VideoGameService videoGameService;

    @BeforeEach
    void init() {
        // Poblar el repositorio compartido con un videojuego de referencia
        VideoGameService videoGameService = new VideoGameService();
        videoGameService.addVideoGame(new VideoGame(1, "The Legend of Zelda", "Adventure", 1998, 29.99, "Nintendo 64"));

        this.reviewService = new ReviewService();
        this.reviewService.createReview("1", "JohnDoe", 9.5, "Masterpiece!", "2024-01-15", videoGameService.getVideoGameById(1));
    }

    // ── createReview ──────────────────────────────────────

    @Test
    void testCreateReviewSuccessful() {
        Assert.assertTrue(this.reviewService.createReview("2", "JaneDoe", 8.0, "Great game!", "2024-02-01", videoGameService.getVideoGameById(1)));
    }

    // ── getAll ────────────────────────────────────────────

    @Test
    void testGetAllNotEmpty() {
        List<Review> reviews = this.reviewService.getAll();
        Assert.assertFalse(reviews.isEmpty());
        Assert.assertEquals(1, reviews.size());
    }

    // ── getReviewById ─────────────────────────────────────

    @Test
    void testGetReviewByIdSuccessful() {
        Review result = this.reviewService.getReviewById(1);
        Assert.assertNotNull(result);
        Assert.assertEquals("JohnDoe", result.getAuthor());
    }

    @Test
    void testGetReviewByIdFailed() {
        Review result = this.reviewService.getReviewById(99);
        Assert.assertNull(result);
    }

    // ── updateReview ──────────────────────────────────────

    @Test
    void testUpdateReviewSuccessful() {
        Assert.assertTrue(this.reviewService.updateReview(1, "JohnDoe Updated", 10.0, "Perfect!", "2024-03-01", 1));
        Assert.assertEquals("JohnDoe Updated", this.reviewService.getReviewById(1).getAuthor());
    }

    @Test
    void testUpdateReviewFailed() {
        Assert.assertFalse(this.reviewService.updateReview(99, "Ghost", 5.0, "Meh", "2024-01-01", 1));
    }

    // ── deleteReview ──────────────────────────────────────

    @Test
    void testDeleteReviewSuccessful() {
        Assert.assertTrue(this.reviewService.deleteReview(1));
    }

    @Test
    void testDeleteReviewFailed() {
        Assert.assertFalse(this.reviewService.deleteReview(99));
    }

}
