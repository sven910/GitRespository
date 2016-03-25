package com.example.wxassistant;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

public class MediaAssistant {
	@SuppressLint("NewApi")
	public Bitmap getVideoThumbnail(String filePath) {  
        Bitmap bitmap = null;  
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();  
        try {  
            retriever.setDataSource(filePath);  
            bitmap = retriever.getFrameAtTime();  
        }   
        catch(IllegalArgumentException e) {  
            e.printStackTrace();  
        }   
        catch (RuntimeException e) {  
            e.printStackTrace();
        }   
        finally {  
            try {  
                retriever.release();  
            }   
            catch (RuntimeException e) {  
                e.printStackTrace();  
            }  
        }  
        return bitmap;  
    }  

	public List<Map<String,Object>> transToBitmap(ArrayList<String> mediaAl){
		List<Map<String,Object>> listitems=new ArrayList<Map<String,Object>>();
		
		
		for(int i=0;i<mediaAl.size();i++){
			Map<String,Object> imagemap=new HashMap<String,Object>();
			Bitmap bm=null;
			bm=getVideoThumbnail(mediaAl.get(i));
			File tmfile = new File(mediaAl.get(i));
			imagemap.put("title", tmfile.getName());
			imagemap.put("image",bm);
			listitems.add(imagemap);
		}
		return listitems;
	}
}
