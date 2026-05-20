package co.edu.uptc.proyecto.domain;

public class Developer {
    private int id;
    private String name;
    private String country;
    private int foundedYear;
    private String email;
    
	public Developer() {
	}

	public Developer(int id, String name, String country, int foundedYear, String email) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.foundedYear = foundedYear;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getFoundedYear() {
		return foundedYear;
	}

	public void setFoundedYear(int foundedYear) {
		this.foundedYear = foundedYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Developer [id=" + id + ", name=" + name + ", country=" + country + ", foundedYear=" + foundedYear
				+ ", email=" + email + "]";
	}
    
}
