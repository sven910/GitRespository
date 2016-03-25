package com.example.wxassistant;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;



public class MainActivity extends Activity {
	public List<Map<String,Object>> listitems=new ArrayList<Map<String,Object>>();
	Hashtable<String,Object> objmap=new Hashtable<String,Object>();
	ArrayList<String> objal=new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		//获取视频文件
		FileAssistant fileAs=new FileAssistant();
		fileAs.getFiles("/mnt/sdcard/tencent/MicroMsg/", 4);
		objal = DataUtil.al;
		//将视频文件转换出对应的缩略图
		MediaAssistant mediaAs=new MediaAssistant();
		listitems=mediaAs.transToBitmap(objal);
		//设置GridView
		setGridView();
	}

	public void setGridView(){
		GridView gv1=(GridView)findViewById(R.id.gridview1);
			BaseAdapter adapter=new BaseAdapter(){
				public View getView(int position,View convertView,ViewGroup parent){
					ImageView imageview;
					TextView textview=new TextView(MainActivity.this);
					if(convertView==null){
						imageview=new ImageView(MainActivity.this);
						imageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
						imageview.setPadding(5,0,5,0);	
					}else{
						imageview=(ImageView)convertView;
					}
					Map<String,Object> imgmap=new HashMap<String,Object>();
					imgmap = listitems.get(position);
					//Image
					Bitmap bm;
					bm=(Bitmap)imgmap.get("image");
					imageview.setImageBitmap(bm);
					//Text
					textview.setText("title");
					return imageview;
				}
				

				public int getCount(){
					return listitems.size();
				}

				@Override
				public long getItemId(int position) {
					// TODO Auto-generated method stub
					return position;
				}

				@Override
				public Object getItem(int position) {
					// TODO Auto-generated method stub
					return position;
				}
			};			
			
		gv1.setAdapter(adapter);
	}
	


}
