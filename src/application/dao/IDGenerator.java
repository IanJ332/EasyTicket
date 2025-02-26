package application.dao;

import java.util.UUID;

public class IDGenerator {
	private static IDGenerator singleInstance = new IDGenerator();
	
	private IDGenerator() {}
	
	public static IDGenerator getIDGenerator() {
		return singleInstance;
	}
	
	/**
	 * Generate a random ID
	 * @return	A random ID in the form of a String
	 */
	public String getRandomID() {
		return UUID.randomUUID().toString();
	}
}
