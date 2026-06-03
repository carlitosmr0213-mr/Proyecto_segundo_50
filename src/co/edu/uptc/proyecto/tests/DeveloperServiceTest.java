package co.edu.uptc.proyecto.tests;



import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.proyecto.domain.Developer;
import co.edu.uptc.proyecto.service.DeveloperService;

class DeveloperServiceTest {

	 private DeveloperService developerService;

	    @BeforeEach
	    void init() {
	        this.developerService = new DeveloperService();
	        this.developerService.createDeveloper("1", "Nintendo", "Japan", 1889, "contact@nintendo.com");
	    }

	    // ── createDeveloper ───────────────────────────────────

	    @Test
	    void testCreateDeveloperSuccessful() {
	        Assert.assertTrue(this.developerService.createDeveloper("2", "Rockstar Games", "USA", 1998, "contact@rockstar.com"));
	    }

	    // ── getAll ────────────────────────────────────────────

	    @Test
	    void testGetAllNotEmpty() {
	        List<Developer> developers = this.developerService.getAll();
	        Assert.assertFalse(developers.isEmpty());
	        Assert.assertEquals(1, developers.size());
	    }

	    // ── getDeveloperById ──────────────────────────────────

	    @Test
	    void testGetDeveloperByIdSuccessful() {
	        Developer result = this.developerService.getDeveloperById(1);
	        Assert.assertNotNull(result);
	        Assert.assertEquals("Nintendo", result.getName());
	    }

	    @Test
	    void testGetDeveloperByIdFailed() {
	        Developer result = this.developerService.getDeveloperById(99);
	        Assert.assertNull(result);
	    }

	    // ── updateDeveloper ───────────────────────────────────

	    @Test
	    void testUpdateDeveloperSuccessful() {
	        Assert.assertTrue(this.developerService.updateDeveloper(1, "Nintendo Co.", "Japan", 1889, "info@nintendo.com"));
	        Assert.assertEquals("Nintendo Co.", this.developerService.getDeveloperById(1).getName());
	    }

	    @Test
	    void testUpdateDeveloperFailed() {
	        Assert.assertFalse(this.developerService.updateDeveloper(99, "Ghost Dev", "Unknown", 2000, "ghost@dev.com"));
	    }

	    // ── deleteDeveloper ───────────────────────────────────

	    @Test
	    void testDeleteDeveloperSuccessful() {
	        Assert.assertTrue(this.developerService.deleteDeveloper(1));
	    }

	    @Test
	    void testDeleteDeveloperFailed() {
	        Assert.assertFalse(this.developerService.deleteDeveloper(99));
	    }

}
