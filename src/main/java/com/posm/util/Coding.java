package com.posm.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Coding {
	public static String getCodingBytime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyMMddHHmmss");
		String newDate=sdf.format(new Date());
		String result="";
		Random random=new Random();
		for(int i=0;i<3;i++) {
			result+=random.nextInt(10);
		}
		return newDate+result;
	}
}
