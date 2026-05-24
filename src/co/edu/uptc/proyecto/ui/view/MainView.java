package co.edu.uptc.proyecto.ui.view;

import java.util.Scanner;

public class MainView {
	private Scanner sc;
	private VideoGameView videoGameStudent;
	
	public MainView() {
		this.sc = new Scanner(System.in);
		this.videoGameStudent = new VideoGameView();
	}
	
	public void runApp() {
		StringBuilder menu = new StringBuilder();
		menu.append("----- MENU PRINCIPAL ----- \n");
		menu.append("\n[1]. Administración de la información de los videojuegos");
		menu.append("\n[2]. Salir");
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
				videoGameStudent.menu();
				break;
			case 2:
				flag = false;
				break;
			default:
				System.out.println("Opción incorrecta");
				break;
			}
		}while(flag);
		
	}
}