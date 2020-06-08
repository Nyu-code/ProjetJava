import java.io.Serializable;

public class Mur implements Serializable {
	public String unMur;
	
	public Mur() {
		this.unMur = "#";
	}
	
	public String toString() {
		return this.unMur;
	}
}
