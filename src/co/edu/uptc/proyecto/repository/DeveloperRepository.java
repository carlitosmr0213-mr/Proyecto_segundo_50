package co.edu.uptc.proyecto.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import co.edu.uptc.proyecto.domain.Developer;
 
public class DeveloperRepository {
 
    private Map<Integer, Developer> developers = new HashMap<>();
 
    public Developer saveDeveloper(Developer developer) {
        developers.put(developer.getId(), developer);
        return developer;
    }
 
    public Developer findDeveloperById(int id) {
        return developers.get(id);
    }
 
    public List<Developer> findAll() {
        return new ArrayList<Developer>(developers.values());
    }
 
    public Developer updateDeveloper(Developer developer) {
        if (!developers.containsKey(developer.getId())) {
            return null;
        }
        developers.put(developer.getId(), developer);
        return developer;
    }
 
    public boolean deleteDeveloper(int id) {
        if (!developers.containsKey(id)) {
            return false;
        }
        developers.remove(id);
        return true;
    }
 
    public boolean existsDeveloper(int id) {
        return developers.containsKey(id);
    }
}
