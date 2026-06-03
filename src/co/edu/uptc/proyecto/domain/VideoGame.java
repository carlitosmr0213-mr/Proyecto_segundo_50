package co.edu.uptc.proyecto.domain;

import java.util.ArrayList;
import java.util.List;

public class VideoGame {
	
	private int id;
	private String title;
	private String genre;
	private int releaseYear;
	private double price;
	private String platform;
	private List<Review> reviews;
	
	public VideoGame() {
	}
	
	public VideoGame(int id, String title, String genre, int releaseYear, double price, String platform) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.price = price;
		this.platform = platform;
	}



	public VideoGame(int id, String title, String genre, int releaseYear, double price, String platform,
			List<Review> reviews) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.price = price;
		this.platform = platform;
		this.reviews = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPlataform() {
		return platform;
	}

	public void setPlatform(String plataform) {
		this.platform = plataform;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "VideoGame [id=" + id + ", title=" + title + ", genre=" + genre + ", releaseYear=" + releaseYear
				+ ", price=" + price + ", platform=" + platform + ", reviews=" + reviews.toString() + "]";
	}
	
	
	
	
	
	
	
	
	

}
