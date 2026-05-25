package co.edu.uptc.proyecto.ui.view;

import java.util.List;
import java.util.Scanner;

import co.edu.uptc.proyecto.domain.Developer;
import co.edu.uptc.proyecto.dto.ResultDTO;
import co.edu.uptc.proyecto.ui.controller.DeveloperController;

public class DeveloperView {
	private Scanner scanner;
	private DeveloperController developerController;

	public DeveloperView() {
		super();
		this.scanner = new Scanner(System.in);
		this.developerController = new DeveloperController();
	}
	
	public void menuDeveloper() {
		int option = -1;
        do {
        	StringBuilder menuDeveloper = new StringBuilder();
        	menuDeveloper.append("\n ------- MENU DE DESARROLLADOR ---- ");
        	menuDeveloper.append("\n[1]. Crear desarrollador");
        	menuDeveloper.append("\n[2]. Mostrar todos los desarrollador");
        	menuDeveloper.append("\n[3]. Buscar desarrollador por código");
        	menuDeveloper.append("\n[4]. Actualizar desarrollador");
        	menuDeveloper.append("\n[5]. Eliminar desarrollador");
        	menuDeveloper.append("\n[0]. Volver");
            System.out.println(menuDeveloper.toString());
            String strOption = scanner.nextLine();
            
            /* Valida si se ingresó un valor numérico*/
            if(!strOption.matches("^\\d$")) {
            	continue;
            }
            
            option = Integer.parseInt(strOption);
            switch (option) {
                case 1 -> createDeveloper();
                case 2 -> listDeveloper();
                case 3 -> {
                	ResultDTO resultDTO = findDeveloper();   	
                	/* Practica de operador ternario */
                	/* Se valida si el estudiante fue encontrado */
                	System.out.println(
                			resultDTO.getDeveloper() != null ? 
                			resultDTO.getDeveloper() : "El Desarrollador no fue encontrado ");
                }
                case 4 -> updateDeveloper();
                case 5 -> deleteDeveloper();
            }
        } while (option != 0);
	}
	
	private void createDeveloper() {
    	/* Long id, String codeUniversity, String firstName,
    		String lastName, String email*/
        System.out.println("*Digite el id (Valor numérico): ");
        String id = scanner.nextLine();
        System.out.println("*Digite el nombre (solo letras)");
        String name = scanner.nextLine();
        System.out.println("*Digite el pais (Solo letras): ");
        String country = scanner.nextLine();
        System.out.println("*Digite el año de fundacion (Solo numeros, Ej: 2005): ");
        String foundedYear = scanner.nextLine();
        System.out.println("*Digite el email (letras y simbolos) ");
        String email = scanner.nextLine();
        
        ResultDTO resultDTO = developerController.addDeveloper(id, name, country, foundedYear,
        		email);
        if(!resultDTO.isSuccessful()) {
        	System.out.println("No se pudo crear el desarrollador por las siguientes validaciones fallidas: ");
        	System.out.println();
        	resultDTO.getListMessageError().forEach((messajeError) -> {
        		System.out.println(messajeError);
        	});
        	return;
        }
        System.out.println("El desarrollador fue creado");
    }
	
	private void listDeveloper() {
        List<Developer> developers = developerController.listDevelopers();
        System.out.println("Lista de desarrollador: ");
        if(developers.isEmpty()) {
        	System.out.println("\nNo hay registros\n");
        	return;
        }
        developers.forEach(System.out::println);
    }
	
	private ResultDTO findDeveloper() {
        System.out.println("*Digite el id del desarrollador (Valor numérico y solo 4): ");
        String id = scanner.nextLine();
        ResultDTO resultDTO = developerController.findDeveloperById(id);
        if(!resultDTO.isSuccessful()) {
        	System.out.println("La información ingresada no pasó las siguientes validaciones: ");
        	resultDTO.getListMessageError().forEach(messajeError -> {
        		System.out.println(messajeError);
        	});
        	return resultDTO;
        }
        if(resultDTO.getDeveloper() == null) {
        	System.out.println("El registro no existe.");
        	return resultDTO;
        }
        return resultDTO;
    }
	
	private void updateDeveloper() {
    	ResultDTO resultDTO = this.findDeveloper();
    	/* Si la ejecución de la búsqueda de estudiante no fue exitosa finaliza */
    	if(!resultDTO.isSuccessful()) {
    		return;
    	}
        System.out.println("Digite el nombre del desarrollador: ( " 
        		+ resultDTO.getDeveloper().getName() + " ) (Presione enter si desea conservar)");
        String name = scanner.nextLine();
        System.out.println("Digite el pais del desarrollador: ( " 
        		+ resultDTO.getDeveloper().getCountry() + " ) (Presione enter si desea conservar)");
        String country = scanner.nextLine();
        System.out.println("Digite el año de fundacion del desarrollador: ( " 
        		+ resultDTO.getDeveloper().getFoundedYear() + ") (Presione enter si desea conservar)");
        String foundedYear = scanner.nextLine();
        System.out.println("Digite el email del desarrollador: ( " 
        		+ resultDTO.getDeveloper().getEmail() + ") (Presione enter si desea conservar)");
        String email = scanner.nextLine();
        
        
        ResultDTO resultUpdateDTO = developerController.updateDeveloper(
        		String.valueOf(resultDTO.getDeveloper().getId()), name, country, Integer.parseInt(foundedYear), email);
        if(resultUpdateDTO.isSuccessful()) {
        	System.out.println(resultUpdateDTO.getMessage());
        }else {
        	System.out.println("Mensaje de error: ");
        	resultUpdateDTO.getListMessageError().forEach(messajeError -> {
        	System.out.println(messajeError);});
        	}
        }
        
        private void deleteDeveloper() {
        System.out.println("Digite el ID:");
        String id = scanner.nextLine();
        ResultDTO resultDTO = developerController.deleteDeveloper(id);
        if(resultDTO.isSuccessful()) {
        	System.out.println(resultDTO.getMessage());
        }else {
            System.out.println("Mensaje de error: ");
           	resultDTO.getListMessageError().forEach(mensajeError -> {
      		System.out.println(mensajeError);});
            }
    }
}
