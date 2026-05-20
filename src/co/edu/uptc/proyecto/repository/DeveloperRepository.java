package co.edu.uptc.proyecto.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import co.edu.uptc.proyecto.domain.Developer;
 
public class DeveloperRepository {
 
    private Map<Integer, Developer> developers = new HashMap<>();
    private int nextId = 1;
 
    public Developer save(Developer developer) {
        developer.setId(nextId++);
        developers.put(developer.getId(), developer);
        return developer;
    }
 
    public Developer findById(int id) {
        return developers.get(id);
    }
 
    public Collection<Developer> findAll() {
        return developers.values();
    }
 
    public Developer update(Developer developer) {
        if (!developers.containsKey(developer.getId())) {
            return null;
        }
        developers.put(developer.getId(), developer);
        return developer;
    }
 
    public boolean delete(int id) {
        if (!developers.containsKey(id)) {
            return false;
        }
        developers.remove(id);
        return true;
    }
 
    public boolean exists(int id) {
        return developers.containsKey(id);
    }
}
