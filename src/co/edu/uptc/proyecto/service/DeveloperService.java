package co.edu.uptc.proyecto.service;

import java.util.List;

import co.edu.uptc.proyecto.domain.Developer;
import co.edu.uptc.proyecto.repository.DeveloperRepository;

public class DeveloperService {
	private DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = new DeveloperRepository();
    }

	public DeveloperService() {
		super();
		
		this.developerRepository = new DeveloperRepository();
	}



	public boolean createDeveloper(String id, String name, String country, int foundedYear, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del desarrollador no puede estar vacío.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido.");
        }
        if (foundedYear < 1970 || foundedYear > 2026) {
            throw new IllegalArgumentException("El año de fundación no es válido.");
        }
        Developer developer = new Developer(Integer.parseInt(id.trim()), name.trim(), country.trim(), foundedYear, email.trim());
        developerRepository.saveDeveloper(developer);
        return true;
    }

    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    public Developer getDeveloperById(int id) {
        Developer developer = developerRepository.findDeveloperById(id);
        if (developer == null) {
            throw new IllegalArgumentException("No existe un desarrollador con ID: " + id);
        }
        return developer;
    }

    public boolean updateDeveloper(int id, String name, String country, int foundedYear, String email) {
        Developer existing = getDeveloperById(id);
        if (name != null && !name.trim().isEmpty()) existing.setName(name.trim());
        if (country != null && !country.trim().isEmpty()) existing.setCountry(country.trim());
        if (foundedYear >= 1970 && foundedYear <= 2026) existing.setFoundedYear(foundedYear);
        if (email != null && email.contains("@")) existing.setEmail(email.trim());
        if (!developerRepository.existsDeveloper(id)) {
			return false;
		}
        developerRepository.updateDeveloper(existing);
        return true;
    }

    public boolean deleteDeveloper(int id) {
        getDeveloperById(id); // valida que exista
        return developerRepository.deleteDeveloper(id);
    }
}
