package com.essar.custom.report.utils;

public class MemoryAllocation {
	
	/**
	 * This method checks the JVM memory allocation. Default numbers:
	 * 
	 * Free memory 121 MB
	 * Max memory 1796 MB
     * Total memory 123 MB
	 */
	public static void checkMemoryAllocation() {
		
		System.out.println();
		System.out.println("Memory allocation:");
		System.out.println("Free memory " + Runtime.getRuntime().freeMemory()/1048576 + " MB");
		System.out.println("Max memory " + Runtime.getRuntime().maxMemory()/1048576 + " MB");
		System.out.println("Total memory " + Runtime.getRuntime().totalMemory()/1048576 + " MB");
		System.out.println("---------------------------------------------");
	}
}
