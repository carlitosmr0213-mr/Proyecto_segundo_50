package co.edu.uptc.proyecto.domain;

public class Review {
    private int id;
    private String author;
    private double score; 
    private String comment;
    private String date;        
    private VideoGame videoGame;    
 
    public Review() {
    	
    }
 
    public Review(int id, String author, double score, String comment, String date, VideoGame videoGame) {
        this.id = id;
        this.author = author;
        this.score = score;
        this.comment = comment;
        this.date = date;
        this.videoGame = videoGame;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public VideoGame getVideoGame() {
		return videoGame;
	}

	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", author=" + author + ", score=" + score + ", comment=" + comment + ", date="
				+ date + ", videoGame=" + videoGame.getTitle() + "]";
	} 
    
	
	
	
}
