package co.edu.uptc.proyecto.tests;


import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.proyecto.domain.Review;
import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.service.ReviewService;

class ReviewServiceTest {
	 	private ReviewService reviewService;
	    private VideoGame videoGame;

	    @BeforeEach
	    void init() {
	        this.reviewService = new ReviewService();
	        this.videoGame = new VideoGame(1, "The Legend of Zelda", "Adventure", 1998, 29.99, "Nintendo 64");
	        this.reviewService.createReview("1", "JohnDoe", 9.5, "Masterpiece!", "15/01/2024", videoGame);
	    }

	    // ── createReview ──────────────────────────────────────

	    @Test
	    void testCreateReviewSuccessful() {
	        Assert.assertTrue(this.reviewService.createReview("2", "JaneDoe", 8.0, "Great!", "01/02/2024", videoGame));
	    }

	    @Test
	    void testCreateReviewFailedNullVideoGame() {
	        Assert.assertFalse(this.reviewService.createReview("3", "Ghost", 5.0, "Meh", "01/01/2024", null));
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
	        Assert.assertTrue(this.reviewService.updateReview(1, "JohnDoe Updated", 10.0, "Perfect!", "20/01/2024", videoGame));
	        Assert.assertEquals("JohnDoe Updated", this.reviewService.getReviewById(1).getAuthor());
	    }

	    @Test
	    void testUpdateReviewFailed() {
	        Assert.assertFalse(this.reviewService.updateReview(99, "Ghost", 5.0, "Meh", "01/01/2024", videoGame));
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
