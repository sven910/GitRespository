package com.example.wxassistant;

import java.io.File;

public class FileAssistant {
	int f_count=0;
	// get Files and add to arrylist.
	public void getFiles(String url, int count) {
		File files = new File(url);
		File[] file = files.listFiles();
			try {
				for (File f : file) {
					if (f.isDirectory()) {
						getFiles(f.getAbsolutePath(),count);
					} else {
						if(isVedioFile(f.getPath())) {
							if(f_count<count) {
								DataUtil.al.add(f.getPath());
							}
							f_count = f_count + 1;
							if(f_count==count){break;}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	// check file format
	public static boolean isVedioFile(String path) {
		for (String format : DataUtil.videoFormatSet) {
			if (path.contains(format)) {
				return true;
			}
		}
		return false;
	}
}
