package epsi.i4.serie;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Season {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int number;
	
	@Temporal(TemporalType.DATE)
	private Calendar firstAired;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Calendar getFirstAired() {
		return firstAired;
	}

	public void setFirstAired(Calendar firstAired) {
		this.firstAired = firstAired;
	}
}
