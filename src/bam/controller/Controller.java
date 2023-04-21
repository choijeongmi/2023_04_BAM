package bam.controller;

import bam.dto.Member;

public abstract class Controller {
	
	public static Member LoginedMember;
	
	public abstract void doAtcion(String cmd, String methodName); 
	
	public abstract void makeTestData();

}
