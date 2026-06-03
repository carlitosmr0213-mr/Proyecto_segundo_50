package co.edu.uptc.proyecto.ui.controller;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.proyecto.domain.Review;
import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.dto.ResultDTO;
import co.edu.uptc.proyecto.service.VideoGameService;

public class VideoGameController {
	
	private VideoGameService videoGameService;

	public VideoGameController() {
		this.videoGameService = new VideoGameService();
	}

	public ResultDTO addVideoGame(String id, String title, String genre, String releaseYear,
			String price, String platform) {
		
		ResultDTO resultDTO = this.validateRequiredFields(id, title, releaseYear, price);		
				
		if(!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		
		validateAlphanumericField("ValidateId", id, "^\\d{1,4}$", true, resultDTO);
		validateAlphanumericField("ValidationTitle", title, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", true, resultDTO);
		validateAlphanumericField("ValidationGenre", genre, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", true, resultDTO);
		validateAlphanumericField("ValidationReleaseYear", releaseYear, "^(19[7-9]\\d|200\\d|201\\d|202[0-6])$", true, resultDTO);
		validateAlphanumericField("ValidationPrice", price, "^[+-]?(\\d+\\.?\\d*|\\.\\d+)$",true, resultDTO);
		validateAlphanumericField("ValidationPlatform", platform, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", false,resultDTO);
					
		if(!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		
		List<Review> reviews = new ArrayList<Review>();
		
		boolean result = videoGameService.addVideoGame(new VideoGame(Integer.parseInt(id), title,
				genre, Integer.parseInt(releaseYear), Double.parseDouble(price), platform, reviews));		
        if(!result) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("Ya existe un videojuego con ese id");
        }
        
        return resultDTO;
		
	}

	private ResultDTO validateRequiredFields(String id, String title, String releaseYear, String price) {
    	ResultDTO resultDTO = new ResultDTO();
    	resultDTO.setSuccessful(true);
    	
    	// Validar que id no sea null ni vacío
        if (id == null || id.trim().isEmpty()) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("El id no puede ser null, ni vacío ");
        }
        
        if (title == null || title.trim().isEmpty()) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("El titulo del juego no puede ser null, ni vacío");
        }
        
        if (releaseYear == null || releaseYear.trim().isEmpty()) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("el año de lanzamiento no puede ser null, ni vacío");
        }
        
        if (price == null || price.trim().isEmpty()) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("el precio no puede ser null, ni vacío");
        }
        
        return resultDTO;
    }
	
	private ResultDTO validateAlphanumericField(String nameValidation, String field, String pattern,
    		boolean required, ResultDTO resultDTO) {
		if ((!required) && field == null || field.trim().isBlank()) {
			return resultDTO;
		}
    	boolean result = field.matches(pattern);
    	if(!result) {
    		resultDTO.setSuccessful(false);
    		resultDTO.getListMessageError().add("Falló la validación denominada:  " + nameValidation);
    	}
    	return resultDTO;
    }
	
	public List<VideoGame> listVideoGames() {
        return videoGameService.getAll();
    }
	
	public ResultDTO findVideoGameById(String id) {
    	ResultDTO resultDTO = validateRequiredFieldsForKey(id);
    	if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
		validateAlphanumericField("ValidateId", id,"^\\d{1,4}$", true, resultDTO);
        if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
        resultDTO.setVideoGame(videoGameService.getVideoGameById(Integer.parseInt(id)));
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
	
	public ResultDTO updateVideoGame(int id, String title, String genre,
			String releaseYear, String price, String platform) {
    	ResultDTO resultDTO = this.findVideoGameById(String.valueOf(id));
    	resultDTO.setSuccessful(true);
    	
        if(title != null && !title.trim().isEmpty()) {
        	validateAlphanumericField("ValidationTitle", title, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", true,
            		resultDTO);
        }
        
        if(genre != null && !genre.trim().isEmpty()) {
        	validateAlphanumericField("ValidationGenre", genre, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", true,
            		resultDTO);
        }
        
        int releaseYearInt = (releaseYear == null || releaseYear.trim().isEmpty()) 
                ? resultDTO.getVideoGame().getReleaseYear() 
                : Integer.parseInt(releaseYear.trim());

        double priceDouble = (price == null || price.trim().isEmpty()) 
                     ? resultDTO.getVideoGame().getPrice() 
                     : Double.parseDouble(price.trim());
        
        if(platform != null && !platform.trim().isEmpty()) {
        	validateAlphanumericField("ValidationGenre", platform, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ]+$", true, resultDTO);
        }
        
        /* Si la información no pasa las validaciones, no debe actualizar */
        if(!resultDTO.getListMessageError().isEmpty()) {
        	return resultDTO;
        }
        
        boolean result = videoGameService.updateVideoGame(new VideoGame(id, title,
				genre, releaseYearInt, priceDouble, platform));
        if(!result) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("El videojuego no fue encontrado.");
        }else {
        	resultDTO.setSuccessful(true);
        	resultDTO.setMessage("Se actualizó el registro");
        }
        
        return resultDTO;
    }

    public ResultDTO deleteVideoGame(String id) {
    	ResultDTO resultDTO = validateRequiredFieldsForKey(id);
    	if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
		validateAlphanumericField("ValidateId", id,"^\\d{1,4}$", true, resultDTO);
        if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
        boolean resultDelete = this.videoGameService.deleteVideoGame(Integer.parseInt(id));
        if(!resultDelete) {
        	resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("El registro no se pudo eliminar");
            return resultDTO;
        }
        resultDTO.setMessage("El registro fue eliminado");
        return resultDTO;
    }
	
	
	

}
