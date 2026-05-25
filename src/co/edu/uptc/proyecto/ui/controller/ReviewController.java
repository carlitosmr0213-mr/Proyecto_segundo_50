package co.edu.uptc.proyecto.ui.controller;

import java.util.List;

import co.edu.uptc.proyecto.domain.Review;
import co.edu.uptc.proyecto.dto.ResultDTO;
import co.edu.uptc.proyecto.service.ReviewService;

public class ReviewController {
	private ReviewService reviewService;

	public ReviewController() {
		super();
		this.reviewService = new ReviewService();
	}
	
	public ResultDTO addReview(String id, String author, double score, String comment, String date, int videoGameId) {
		ResultDTO resultDTO = this.validateRequiredFields(id, author, score, comment, date, videoGameId) ;
		
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		
		validateNumericField(String.valueOf(score), resultDTO);
		validateNumericField(String.valueOf(videoGameId), resultDTO);
		validateAlphanumericField("ValidationAuthor", author, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
		validateAlphanumericField("ValidationComment", comment, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", resultDTO);
		
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		
		boolean result = reviewService.createReview(id, author, score, comment, date, videoGameId);		
        if(!result) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("Ya existe un review con ese id");
        }
        
        return resultDTO;
	}
	
	private ResultDTO validateRequiredFields(String id, String author, double score, String comment, String date, int videoGameId) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);
		
		if (id == null || id.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id no puede ser null, ni vacío ");
		}
		if (author == null || author.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El autor no puede ser null, ni vacío ");
		}
		if (score < 0) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id no puede ser menor que 0");
		}
		if (comment == null || comment.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El comentario no puede ser null, ni vacío ");
		}
		if (date == null || date.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La fecha no puede ser null, ni vacío ");
		}
		if (videoGameId <= 0 ) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El ide del videojuego debe ser mayor a cero");
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
	
	public List<Review> listReview(){
		return reviewService.getAll();
	}
	
	public ResultDTO findReviewById(String id) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(id);
		if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
    	validateNumericField(id, resultDTO);
        if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
        resultDTO.setReview(reviewService.getReviewById(Integer.parseInt(id)));
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
	
	public ResultDTO updateReview(String id, String author, double score, String comment, String date, int videoGameId) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setSuccessful(true);
		
		if (id == null || id.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id no puede ser null, ni vacío ");
		}
		if (author == null || author.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El autor no puede ser null, ni vacío ");
		}
		if (score < 0) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id no puede ser menor que 0");
		}
		if (comment == null || comment.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El comentario no puede ser null, ni vacío ");
		}
		if (date == null || date.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La fecha no puede ser null, ni vacío ");
		}
		
		boolean result = reviewService.updateReview(Integer.parseInt(id), author, score, comment, date);
		if (!result) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La review no fue encontrado");
		}else {
			resultDTO.setSuccessful(true);
			resultDTO.getListMessageError().add("Se actualizo el registro");
		}
		return resultDTO;
	}
	
	public ResultDTO deleteReview(String id) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(id);
    	if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
    	validateNumericField(id, resultDTO);
        if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
        boolean resultDelete = this.reviewService.deleteReview(Integer.parseInt(id));
        if(!resultDelete) {
        	resultDTO.setSuccessful(false);
            resultDTO.getListMessageError().add("El registro no se pudo eliminar");
            return resultDTO;
        }
        resultDTO.setMessage("El registro fue eliminado");
        return resultDTO;
	}
}
