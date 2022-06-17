package upravljanje;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pomocna")
public class Pomocna{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int br;

	@Column(name = "x")
	private int x;

	@Column(name = "y")
	private int y;

	public int getBr() {
		return br;
	}

	public void setBr(int br) {
		this.br = br;
	}


	public Pomocna(int br, int x, int y) {
		super();
		this.br = br;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Pomocna() {
		super();
	}
	

}
