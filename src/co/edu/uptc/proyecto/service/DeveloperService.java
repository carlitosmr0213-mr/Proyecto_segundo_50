package co.edu.uptc.proyecto.service;

import java.util.List;

import co.edu.uptc.proyecto.domain.Developer;
import co.edu.uptc.proyecto.repository.DeveloperRepository;

public class DeveloperService {
	private DeveloperRepository developerRepository;

	public DeveloperService() {
		super();
		this.developerRepository = new DeveloperRepository();
	}



	public boolean createDeveloper(String id, String name, String country, int foundedYear, String email) {
        Developer developer = new Developer(Integer.parseInt(id.trim()), name.trim(), country.trim(), foundedYear, email.trim());
        developerRepository.saveDeveloper(developer);
        return true;
    }

    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    public Developer getDeveloperById(int id) {
        Developer developer = developerRepository.findDeveloperById(id);
        return developer;
    }

    public boolean updateDeveloper(int id, String name, String country, int foundedYear, String email) {
    	if (!developerRepository.existsDeveloper(id)) {
			return false;
		}
    	Developer existing = getDeveloperById(id);
        
        developerRepository.updateDeveloper(existing);
        return true;
    }

    public boolean deleteDeveloper(int id) {
        getDeveloperById(id); // valida que exista
        return developerRepository.deleteDeveloper(id);
    }
}
