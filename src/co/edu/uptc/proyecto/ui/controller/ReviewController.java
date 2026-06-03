
package co.edu.uptc.proyecto.ui.controller;

import java.util.List;

import co.edu.uptc.proyecto.domain.Review;
import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.dto.ResultDTO;
import co.edu.uptc.proyecto.service.ReviewService;
import co.edu.uptc.proyecto.service.VideoGameService;

public class ReviewController {
	private ReviewService reviewService;
	private VideoGameService videoGameService;

	public ReviewController() {
		super();
		this.reviewService = new ReviewService();
		this.videoGameService = new VideoGameService();
	}
	
	public ResultDTO addReview(String id, String author, String score, String comment, String date, String videoGameId) {
		ResultDTO resultDTO = this.validateRequiredFields(id, author, score, comment, date, videoGameId) ;
		
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		
		validateAlphanumericField("ValidateId", id, "^\\d{1,4}$", true, resultDTO);
		validateAlphanumericField("ValidationDate", date, "^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/(19|20)\\d{2}$", false, resultDTO);
		validateAlphanumericField("ValidationScore", String.valueOf(score), "^[+-]?(\\d+\\.?\\d*|\\.\\d+)$", true, resultDTO);
		validateAlphanumericField("ValidateVideoGameId", String.valueOf(videoGameId),"^\\d{1,4}$", true, resultDTO);
		validateAlphanumericField("ValidationAuthor", author, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", true, resultDTO);
		validateAlphanumericField("ValidationComment", comment, "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", true,resultDTO);
		
		if (!resultDTO.isSuccessful()) {
			return resultDTO;
		}
		
		VideoGame videoGame = videoGameService.getVideoGameById(Integer.parseInt(videoGameId));
		
	    if (videoGame == null) {
	        resultDTO.setSuccessful(false);
	        resultDTO.getListMessageError().add("No existe un videojuego con ese id");
	        return resultDTO; // ✅ Sale aquí, no llega a createReview
	    }
	    
		boolean result = reviewService.createReview(id, author, Double.parseDouble(score), comment, date, videoGameService.getVideoGameById(Integer.parseInt(videoGameId)));		
		
		if(!result) {
        	resultDTO.setSuccessful(false);
        	resultDTO.getListMessageError().add("Ya existe una reseña con ese id");
        }
        return resultDTO;
	}
	
	private ResultDTO validateRequiredFields(String id, String author, String score, String comment, String date, String videoGameId) {
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
		if (score == null || score.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del videojuego debe ser mayor a cero");
		}
		if (comment == null || comment.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El comentario no puede ser null, ni vacío ");
		}
		if (date == null || date.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La fecha no puede ser null, ni vacío ");
		}
		if (videoGameId == null || videoGameId.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id del videojuego debe ser mayor a cero");
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
	
	public List<Review> listReview(){
		return reviewService.getAll();
	}
	
	public ResultDTO findReviewById(String id) {
		ResultDTO resultDTO = validateRequiredFieldsForKey(id);
		if(!resultDTO.isSuccessful()) {
    		return resultDTO;
    	}
		validateAlphanumericField("ValidateId", id, "^\\d{1,4}$", true, resultDTO);
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
	
	public ResultDTO updateReview(String id, String author, String score, String comment, String date, String videoGameId) {
		ResultDTO resultDTO = this.findReviewById(id);
		resultDTO.setSuccessful(true);
		
		if (id == null || id.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El id no puede ser null, ni vacío ");
		}
		if (author == null || author.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El autor no puede ser null, ni vacío ");
		}
		 double scoreDouble = (score == null || score.trim().isEmpty()) 
                 ? resultDTO.getReview().getScore() 
                 : Double.parseDouble(score.trim());
		if (comment == null || comment.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("El comentario no puede ser null, ni vacío ");
		}
		if (date == null || date.trim().isEmpty()) {
			resultDTO.setSuccessful(false);
			resultDTO.getListMessageError().add("La fecha no puede ser null, ni vacío ");
		}
		int videoGameInt = (videoGameId == null || videoGameId.trim().isEmpty()) 
                ? resultDTO.getVideoGame().getReleaseYear() 
                : Integer.parseInt(videoGameId.trim());
		
		boolean result = reviewService.updateReview(Integer.parseInt(id), author, scoreDouble, comment, date, videoGameService.getVideoGameById(videoGameInt));
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
		validateAlphanumericField("ValidateId", id,"^\\d{1,4}$", true, resultDTO);
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
