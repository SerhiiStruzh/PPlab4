package recomendation;

public class RecommendTicketData {
	private String start, end, comfLevel;
	double dist;
	
	public RecommendTicketData(String start, String end, String comfLevel, double dist) {
		this.setStart(start);
		this.setEnd(end);
		this.setComfLevel(comfLevel);
		this.dist = dist;
	}

	@Override
	public String toString() {
		return "Start: " + start + ", End: " + end + ", Comfort Level: " + comfLevel + ", Distance: " + dist;
	}

	public String getComfLevel() {
		return comfLevel;
	}

	public void setComfLevel(String comfLevel) {
		this.comfLevel = comfLevel;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	public void setDistance(double dist) {
		this.dist = dist;
	}
	
	public double getDistance() {
		return dist;
	}
	
}
