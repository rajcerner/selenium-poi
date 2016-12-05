package com.cerner.pctorion.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jk048034
 *
 */
public class TestUtils {
	
	/**
	 * Get Current time stamp
	 * @return
	 */
	public static String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");
		return format.format(date);
	}

	/**
	 * Get relative path of the framework
	 * @return
	 */
	public static String getRelativePath() {
		String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		if (relativePath.endsWith("bin")) {
			relativePath = new File(System.getProperty("user.dir")).getParent();
		}
		return relativePath;
	}
	
	
}
