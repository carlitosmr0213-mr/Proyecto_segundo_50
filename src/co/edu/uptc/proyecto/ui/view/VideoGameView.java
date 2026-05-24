package co.edu.uptc.proyecto.ui.view;

import java.util.List;
import java.util.Scanner;

import co.edu.uptc.proyecto.domain.VideoGame;
import co.edu.uptc.proyecto.dto.ResultDTO;
import co.edu.uptc.proyecto.ui.controller.VideoGameController;

public class VideoGameView {

    private Scanner scanner;
    private VideoGameController videoGameController;

    public VideoGameView() {
        this.scanner = new Scanner(System.in);
        this.videoGameController = new VideoGameController();
    }

    public void menu() {
        int option = -1;
        do {
        	StringBuilder menuVideoGame = new StringBuilder();
        	menuVideoGame.append("\n ------- MENU DE VIDEOJUEGOS ---- ");
        	menuVideoGame.append("\n[1]. Crear videojuego");
        	menuVideoGame.append("\n[2]. Mostrar todos los videojuegos");
        	menuVideoGame.append("\n[3]. Buscar videojuego por código");
        	menuVideoGame.append("\n[4]. Actualizar videojuego");
        	menuVideoGame.append("\n[5]. Eliminar videojuego");
        	menuVideoGame.append("\n[0]. Volver");
            System.out.println(menuVideoGame.toString());
            String strOption = scanner.nextLine();
            
            /* Valida si se ingresó un valor numérico*/
            if(!strOption.matches("^\\d$")) {
            	continue;
            }
            
            option = Integer.parseInt(strOption);
            switch (option) {
                case 1 -> createVideoGame();
                case 2 -> listVideoGames();
                case 3 -> {
                	ResultDTO resultDTO = findVideoGame();   	
                	/* Practica de operador ternario */
                	/* Se valida si el estudiante fue encontrado */
                	System.out.println(
                			resultDTO.getVideoGame() != null ? 
                			resultDTO.getVideoGame() : "El Videojuego no fue encontrado ");
                }
                case 4 -> updateVideoGame();
                case 5 -> deleteVideoGame();
            }
        } while (option != 0);
    }

    private void createVideoGame() {
    	/* Long id, String codeUniversity, String firstName,
    		String lastName, String email*/
        System.out.println("*Digite el id (Valor numérico): ");
        String id = scanner.nextLine();
        System.out.println("*Digite el titulo (solo letras)");
        String title = scanner.nextLine();
        System.out.println("*Digite el genero (Solo letras): ");
        String genre = scanner.nextLine();
        System.out.println("*Digite el año de lanzamiendo (Solo nuemeros, Ej: 2005): ");
        String releaseYear = scanner.nextLine();
        System.out.println("*Digite el precio (solo numeros) ");
        String price = scanner.nextLine();
        System.out.println("*Digite la plataforma dedescarga (solo letras) ");
        String platform = scanner.nextLine();
        
        ResultDTO resultDTO = videoGameController.addVideoGame(id, title, genre, releaseYear,
        		price, platform);
        if(!resultDTO.isSuccessful()) {
        	System.out.println("No se pudo crear el videojuego por las siguientes validaciones fallidas: ");
        	System.out.println();
        	resultDTO.getListMessageError().forEach((messajeError) -> {
        		System.out.println(messajeError);
        	});
        	return;
        }
        System.out.println("El estudiante fue creado");
    }

    private void listVideoGames() {
        List<VideoGame> videoGames = videoGameController.listVideoGames();
        System.out.println("Lista de estudiantes: ");
        if(videoGames.isEmpty()) {
        	System.out.println("\nNo hay registros\n");
        	return;
        }
        videoGames.forEach(System.out::println);
    }

    private ResultDTO findVideoGame() {
        System.out.println("*Digite el id del videojuego (Valor numérico y solo 4): ");
        String id = scanner.nextLine();
        ResultDTO resultDTO = videoGameController.findStudentById(id);
        if(!resultDTO.isSuccessful()) {
        	System.out.println("La información ingresada no pasó las siguientes validaciones: ");
        	resultDTO.getListMessageError().forEach(messajeError -> {
        		System.out.println(messajeError);
        	});
        	return resultDTO;
        }
        if(resultDTO.getVideoGame() == null) {
        	System.out.println("El registro no existe.");
        	return resultDTO;
        }
        return resultDTO;
    }

    private void updateVideoGame() {
    	/* Reutilizamos el método de buscar estudiante */
    	/* En la instancia ResultDTO, hay un objeto de StudentDTO que contiene la información 
    	 * obtenida de la consulta */
    	ResultDTO resultDTO = this.findVideoGame();
    	/* Si la ejecución de la búsqueda de estudiante no fue exitosa finaliza */
    	if(!resultDTO.isSuccessful()) {
    		return;
    	}
        System.out.println("Digite el titulo del videojuego: ( " 
        		+ resultDTO.getVideoGame().getTitle() + " ) (Presione enter si desea conservar)");
        String title = scanner.nextLine();
        System.out.println("Digite el genero del videojuego: ( " 
        		+ resultDTO.getVideoGame().getGenre() + " ) (Presione enter si desea conservar)");
        String genre = scanner.nextLine();
        System.out.println("Digite el año de lanzamiento del videojuego: ( " 
        		+ resultDTO.getVideoGame().getReleaseYear() + ") (Presione enter si desea conservar)");
        String releaseYear = scanner.nextLine();
        System.out.println("Digite el precio del videojuego: ( " 
        		+ resultDTO.getVideoGame().getPrice() + ") (Presione enter si desea conservar)");
        String price = scanner.nextLine();
        System.out.println("Digite la plataforma del videjuego: ( " 
        		+ resultDTO.getVideoGame().getPlataform() + ") (Presione enter si desea conservar)");
        String platform = scanner.nextLine();
        
        
        ResultDTO resultUpdateDTO = videoGameController.updateVideoGame(
        		resultDTO.getVideoGame().getId(), title,genre,releaseYear,price,platform);
        if(resultUpdateDTO.isSuccessful()) {
        	System.out.println(resultUpdateDTO.getMessage());
        }else {
        	resultUpdateDTO.getListMessageError().forEach(messajeError -> {
        		System.out.println(messajeError);
        	});
        }
    }

    private void deleteVideoGame() {
        System.out.println("Digite el ID:");
        String id = scanner.nextLine();
        ResultDTO resultDTO = videoGameController.deleteVideoGame(id);
        if(resultDTO.isSuccessful()) {
        	System.out.println(resultDTO.getMessage());
        }else {
        	System.out.println("Mensaje de error: ");
        	resultDTO.getListMessageError().forEach(mensajeError -> {
        		System.out.println(mensajeError);
        	});
        }
    }

}