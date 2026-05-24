package co.edu.uptc.proyecto.dto;

import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.proyecto.domain.VideoGame;

public class ResultDTO {
	private boolean isSuccessful;
	private String message;
	private VideoGame videoGame;
	private List<String> listMessageError;
	
	public ResultDTO() {
		this.listMessageError = new ArrayList<>();
	}
	
	/**
	 * Método encargado de retornar el valor de isSuccessful.
	 *
	 * @return valor de isSuccessful
	 */
	public boolean isSuccessful() {
		return isSuccessful;
	}
	/**
	 * Método encargado de establecer el valor de isSuccessful.
	 *
	 * @param isSuccessful nuevo valor de isSuccessful
	 */
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	/**
	 * Método encargado de retornar el valor de message.
	 *
	 * @return valor de message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Método encargado de retornar el valor de student.
	 *
	 * @return valor de student
	 */
	public VideoGame getVideoGame() {
		return videoGame;
	}

	/**
	 * Método encargado de establecer el valor de student.
	 *
	 * @param student nuevo valor de student
	 */
	public void setStudent(VideoGame videoGame) {
		this.videoGame = videoGame;
	}

	/**
	 * Método encargado de establecer el valor de message.
	 *
	 * @param message nuevo valor de message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Método encargado de retornar el valor de listMessageError.
	 *
	 * @return valor de listMessageError
	 */
	public List<String> getListMessageError() {
		return listMessageError;
	}

	/**
	 * Método encargado de establecer el valor de listMessageError.
	 *
	 * @param listMessageError nuevo valor de listMessageError
	 */
	public void setListMessageError(List<String> listMessageError) {
		this.listMessageError = listMessageError;
	}

}
