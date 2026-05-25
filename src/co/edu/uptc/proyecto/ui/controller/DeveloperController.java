package co.edu.uptc.proyecto.ui.controller;

import java.util.List;

import co.edu.uptc.proyecto.domain.Developer;
import co.edu.uptc.proyecto.dto.ResultDTO;
import co.edu.uptc.proyecto.service.DeveloperService;

public class DeveloperController {
	private DeveloperService developerService;

	public DeveloperController() {
		super();
		this.developerService = new DeveloperService();
	}
	
	public ResultDTO addDeveloper(String id, String name, String country, String foundedYear, String email) {
		ResultDTO resultDTO = this.validateRequiredFields(id, name, country, foundedYear, email) ;
		
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		
		validateNumericField(id, resultDTO);
		validateAlphanumericField("ValidationName", name, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
		validateAlphanumericField("ValidationCountry", country, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$",
				resultDTO);
		validateNumericField(foundedYear, resultDTO);
		validateAlphanumericField("ValidationEmail", email, "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", resultDTO);
		
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		
		boolean result = developerService.createDeveloper(id, name, country, Integer.parseInt(foundedYear), email);		
        if(!result) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("Ya existe un desarrollador con ese id");
        }
        
        return resultDTO;
	}
	
	private ResultDTO validateRequiredFields(String id, String name, String country, String foundedYear, String email) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);
		
		if (id == null || id.trim().isEmpty()) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("El id no puede ser null, ni vacío ");
        }
		
		if (name == null || name.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El nombre no puede ser nulo, ni vacio");
		}
		
		if (country == null || country.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El país no puede ser nulo, ni vacio");
		}
		
		if (foundedYear == null || foundedYear.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El año no puede ser nulo, ni vacio, tampoco mayor al año actual");
		}
		
		if (email == null || email.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El país no puede ser nulo, ni vacio");
		}
		
		return resultDTO;
		
 	}
	
	private ResultDTO validateAlphanumericField(String nameValidation, String field, String pattern,
    		ResultDTO resultDTO) {
    	boolean result = field.matches(pattern);
    	if(!result) {
    		resultDTO.setSuccessful(false);
    		resultDTO.getListMessageError().add("Falló la validación denominada:  " + nameValidation);
    	}
    	return resultDTO;
    }
	
	private ResultDTO validateNumericField(String field, ResultDTO resultDTO) {
    	boolean result = field.matches("^\\d{4}$");
    	if(!result) {
    		resultDTO.setSuccessful(false);
    		resultDTO.getListMessageError().add("El ID debe tener solo 4 caracteres");
    	}
    	return resultDTO;
    }
	
	public List<Developer> listDevelopers (){
		return developerService.getAll();
	}
	
	public ResultDTO findDeveloperById(String id) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(id);
		if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
    	validateNumericField(id, resultDTO);
        if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
        resultDTO.setDeveloper(developerService.getDeveloperById(Integer.parseInt(id)));
        return resultDTO;
	}
	
	private ResultDTO validateRequiredFieldsForKey(String id) {
    	ResultDTO resultDTO = new ResultDTO();
    	resultDTO.setSuccessful(true);
        if (id == null || id.trim().isEmpty()) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("El id no puede ser null ni vacío");
        }   
        return resultDTO;
    }
	
	public ResultDTO updateDeveloper(String id, String name, String country, int foundedYear, String email) {
		ResultDTO resultDTO = new ResultDTO();
    	resultDTO.setSuccessful(true);
    	

		if (id == null || id.trim().isEmpty()) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("El id no puede ser null, ni vacío ");
        }
		
		if (name == null || name.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El nombre no puede ser nulo, ni vacio");
		}
		
		if (country == null || country.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El país no puede ser nulo, ni vacio");
		}
		
		if (foundedYear <= 0 || foundedYear > java.time.Year.now().getValue()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El año no puede ser nulo, ni vacio, tampoco mayor al año actual");
		}
		
		if (email == null || email.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El país no puede ser nulo, ni vacio");
		}
    	
		boolean result = developerService.updateDeveloper(Integer.parseInt(id), name, country, foundedYear, email);
		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El desarrollador no fue encontrado.");
		}else {
			resultDTO.setSuccessful(true);
			resultDTO.getListMessageError().add("Se actualizo el registro");
		}
		
		return resultDTO;
	}
	
	public ResultDTO deleteDeveloper(String id) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(id);
    	if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
    	validateNumericField(id, resultDTO);
        if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
        boolean resultDelete = this.developerService.deleteDeveloper(Integer.parseInt(id));
        if(!resultDelete) {
        	resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("El registro no se pudo eliminar");
            return resultDTO;
        }
        resultDTO.setMessage("El registro fue eliminado");
        return resultDTO;
	}
	
}
