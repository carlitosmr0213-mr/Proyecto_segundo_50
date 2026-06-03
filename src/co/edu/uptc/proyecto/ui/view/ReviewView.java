package co.edu.uptc.proyecto.ui.view;

import java.util.List;
import java.util.Scanner;

import co.edu.uptc.proyecto.domain.Review;
import co.edu.uptc.proyecto.dto.ResultDTO;
import co.edu.uptc.proyecto.ui.controller.ReviewController;

public class ReviewView {
	private Scanner scanner;
	private ReviewController reviewController;
	
	public ReviewView() {
		super();
		this.scanner = new Scanner(System.in);
		this.reviewController = new ReviewController();
	}
	
	public void menuReview() {
		int option = -1;
        do {
        	StringBuilder menuReview = new StringBuilder();
        	menuReview.append("\n ------- MENU DE REVIEW ---- ");
        	menuReview.append("\n[1]. Crear review");
        	menuReview.append("\n[2]. Mostrar todos los review");
        	menuReview.append("\n[3]. Buscar review por código");
        	menuReview.append("\n[4]. Actualizar review");
        	menuReview.append("\n[5]. Eliminar review");
        	menuReview.append("\n[0]. Volver");
            System.out.println(menuReview.toString());
            String strOption = scanner.nextLine();
            
            /* Valida si se ingresó un valor numérico*/
            if(!strOption.matches("^\\d$")) {
            	continue;
            }
            
            option = Integer.parseInt(strOption);
            switch (option) {
                case 1 -> createReview();
                case 2 -> listReview();
                case 3 -> {
                	ResultDTO resultDTO = findReview();
                	System.out.println(
                			resultDTO.getReview() != null ? 
                			resultDTO.getReview() : "El review no fue encontrado ");
                }
                case 4 -> updateReview();
                case 5 -> deleteReview();
            }
        } while (option != 0);
	}
	
	private void createReview() {
		System.out.println("*Digite el id del review (Valor numérico): ");
        String id = scanner.nextLine();
        System.out.println("*Digite el nombre del autor (solo letras)");
        String author = scanner.nextLine();
        System.out.println("*Digite el puntaje (Solo letras): ");
        String score = scanner.nextLine();
        System.out.println("*Digite el comentario (Solo letras): ");
        String comment = scanner.nextLine();
        System.out.println("*Digite la fecha (DD/MM/YYYY) ");
        String date = scanner.nextLine();
        System.out.println("*Digite el id del videojuego (Valor numérico): ");
        String idVideoGame = scanner.nextLine();
        
        ResultDTO resultDTO = reviewController.addReview(id, author, score, comment,
        		date, idVideoGame);
        if(!resultDTO.isSuccessful()) {
        	System.out.println("No se pudo crear el review por las siguientes validaciones fallidas: ");
        	System.out.println();
        	resultDTO.getListMessageError().forEach((messajeError) -> {
        		System.out.println(messajeError);
        	});
        	return;
        }
        System.out.println("El review fue creado");
	}
	
	private void listReview() {
        List<Review> reviews = reviewController.listReview();
        System.out.println("Lista de reviews: ");
        if(reviews.isEmpty()) {
        	System.out.println("\nNo hay registros\n");
        	return;
        }
        reviews.forEach(System.out::println);
    }
	
	private ResultDTO findReview() {
        System.out.println("*Digite el id del review (Valor numérico y solo 4): ");
        String id = scanner.nextLine();
        ResultDTO resultDTO = reviewController.findReviewById(id);
        if(!resultDTO.isSuccessful()) {
        	System.out.println("La información ingresada no pasó las siguientes validaciones: ");
        	resultDTO.getListMessageError().forEach(messajeError -> {
        		System.out.println(messajeError);
        	});
        	return resultDTO;
        }
        if(resultDTO.getReview() == null) {
        	System.out.println("El registro no existe.");
        	return resultDTO;
        }
        return resultDTO;
    }
	
	private void updateReview() {
    	ResultDTO resultDTO = this.findReview();
    	if(!resultDTO.isSuccessful()) {
    		return;
    	}
    	
    	if (resultDTO.getReview() == null) {
	        System.out.println("Error: no se encontró el review.");
	        return;
	    }
    	
        System.out.println("Digite el autor del review: ( " 
        		+ resultDTO.getReview().getAuthor() + " ) (Presione enter si desea conservar)");
        String author = scanner.nextLine();
        System.out.println("Digite el puntaje del review: ( " 
        		+ resultDTO.getReview().getScore() + " ) (Presione enter si desea conservar)");
        String score = scanner.nextLine();
        System.out.println("Digite el comentario del review: ( " 
        		+ resultDTO.getReview().getComment() + ") (Presione enter si desea conservar)");
        String comment = scanner.nextLine();
        System.out.println("Digite la fecha del review (DD/MM/YYYY): ( " 
        		+ resultDTO.getReview().getDate() + ") (Presione enter si desea conservar)");
        String date = scanner.nextLine();
        
        
        ResultDTO resultUpdateDTO = reviewController.updateReview(
        		String.valueOf(resultDTO.getReview().getId()), author, score, comment, date, String.valueOf(resultDTO.getReview().getVideoGame().toString()));
        if(resultUpdateDTO.isSuccessful()) {
        	System.out.println(resultUpdateDTO.getMessage());
        }else {
        	System.out.println("Mensaje de error: ");
        	resultUpdateDTO.getListMessageError().forEach(messajeError -> {
        	System.out.println(messajeError);});
        	}
        }
	
	private void deleteReview() {
        System.out.println("Digite el ID:");
        String id = scanner.nextLine();
        ResultDTO resultDTO = reviewController.deleteReview(id);
        if(resultDTO.isSuccessful()) {
        	System.out.println(resultDTO.getMessage());
        }else {
            System.out.println("Mensaje de error: ");
           	resultDTO.getListMessageError().forEach(mensajeError -> {
      		System.out.println(mensajeError);});
            }
    }
}
