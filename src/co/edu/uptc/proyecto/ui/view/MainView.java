package co.edu.uptc.proyecto.ui.view;

import java.util.Scanner;

public class MainView {
	private Scanner sc;
	private VideoGameView videoGameView;
	private DeveloperView developerView;
	private ReviewView reviewView;
	
	public MainView() {
		this.sc = new Scanner(System.in);
		this.videoGameView = new VideoGameView();
		this.developerView = new DeveloperView();
		this.reviewView = new ReviewView();
		}
	
	public void runApp() {
		StringBuilder menu = new StringBuilder();
		menu.append("----- MENU PRINCIPAL ----- \n");
		menu.append("\n[1]. Administración de la información de los videojuegos");
		menu.append("\n[2]. Administración de la información de los desarrolladores");
		menu.append("\n[3]. Administración de la información de los review");
		menu.append("\n[0]. Salir");
		boolean flag = true;
		do {
			System.out.println(menu.toString());
			String strOption = sc.nextLine();
			
			/* Valida si se ingresó un valor numérico*/
            if(!strOption.matches("^\\d$")) {
            	continue;
            }
            
            int op = Integer.parseInt(strOption); 
            
			switch(op) {
			case 1:
				videoGameView.menu();
				break;
			case 2:
				developerView.menuDeveloper();
				break;
			case 3:
				reviewView.menuReview();
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("Opción incorrecta");
				break;
			}
		}while(flag);
		
	}
}