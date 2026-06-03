package co.edu.uptc.proyecto.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.service.VideoGameService;

class VideoGameServiceTest {

	private VideoGameService videoGameService;

    @BeforeEach
    void init() {
        this.videoGameService = new VideoGameService();
        VideoGame vg1 = new VideoGame(1, "The Legend of Zelda", "Adventure", 1998, 20000, "Nintendo 64");
        this.videoGameService.addVideoGame(vg1);
    }

    // ── addVideoGame ──────────────────────────────────────

    @Test
    void testAddVideoGameSuccessful() {
        VideoGame vg2 = new VideoGame(2, "Mario Kart", "Racing", 2020, 50000, "Nintendo Switch");
        Assert.assertTrue(this.videoGameService.addVideoGame(vg2));
    }

    // ── getAll ────────────────────────────────────────────

    @Test
    void testGetAllNotEmpty() {
        List<VideoGame> games = this.videoGameService.getAll();
        Assert.assertFalse(games.isEmpty());
        Assert.assertEquals(1, games.size());
    }

    // ── getVideoGameById ──────────────────────────────────

    @Test
    void testGetVideoGameByIdSuccessful() {
        VideoGame result = this.videoGameService.getVideoGameById(1);
        Assert.assertNotNull(result);
        Assert.assertEquals("The Legend of Zelda", result.getTitle());
    }

    @Test
    void testGetVideoGameByIdFailed() {
        VideoGame result = this.videoGameService.getVideoGameById(99);
        Assert.assertNull(result);
    }

    // ── updateVideoGame ───────────────────────────────────

    @Test
    void testUpdateVideoGameSuccessful() {
        VideoGame update = new VideoGame(1, "Zelda: Ocarina of Time", "RPG", 1998, 39000.0, "Nintendo 64");
        Assert.assertTrue(videoGameService.updateVideoGame(update));
        Assert.assertEquals("Zelda: Ocarina of Time",
                videoGameService.getVideoGameById(1).getTitle());
    }

    @Test
    void testUpdateVideoGameFailed() {
        VideoGame update = new VideoGame(99, "Ghost Game", "Action", 2022, 50000, "PS5");
        Assert.assertFalse(this.videoGameService.updateVideoGame(update));
    }

    // ── deleteVideoGame ───────────────────────────────────

    @Test
    void testDeleteVideoGameSuccessful() {
        Assert.assertTrue(this.videoGameService.deleteVideoGame(1));
    }

    @Test
    void testDeleteVideoGameFailed() {
        Assert.assertFalse(this.videoGameService.deleteVideoGame(99));
    }
	
}
