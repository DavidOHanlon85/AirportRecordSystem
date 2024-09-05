package p3;

import java.util.Comparator;

public class CompareByAltitude implements Comparator<Airport> {

	@Override
	public int compare(Airport o1, Airport o2) {
	
		return o1.getAlt() - o2.getAlt();
	}

}
