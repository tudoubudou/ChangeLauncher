package com.example.changelauncher;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btn1,btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//add  start 

			    String examplePackageName = "com.baidu.home2"; //请修改为需要设置的 package name
			    String exampleActivityName = "com.baidu.launcher.app.Launcher"; //请修改为需要设置的 launcher activity name
			   
			           Intent intent=new Intent(Intent.ACTION_MAIN);
			           intent.addCategory(Intent.CATEGORY_HOME);
			          
			           List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, 0);
			           if(resolveInfoList != null){
			                    int size = resolveInfoList.size();
			                    for(int j=0;j<size;){
			                             final ResolveInfo r = resolveInfoList.get(j);
			                             if(!r.activityInfo.packageName.equals(examplePackageName)){
			                                       resolveInfoList.remove(j);
			                                       size -= 1;
			                              }else
			                              {
			                                      j++;
			                              }
			                    }

			                    ComponentName[] set = new ComponentName[size];
			                    ComponentName defaultLauncher=new ComponentName(examplePackageName, exampleActivityName);

			                    int defaultMatch=0;
			                    for(int i=0;i<size;i++){
			                             final ResolveInfo resolveInfo = resolveInfoList.get(i);
			                             set[i] = new ComponentName(resolveInfo.activityInfo.packageName,resolveInfo.activityInfo.name);
			                             if(resolveInfo.match > defaultMatch){
			                                       defaultMatch = resolveInfo.match;
			                             }
			                    }


			                    IntentFilter filter=new IntentFilter();
			                    filter.addAction(Intent.ACTION_MAIN);
			                    filter.addCategory(Intent.CATEGORY_HOME);
			                    filter.addCategory(Intent.CATEGORY_DEFAULT);
			                   
			                    getPackageManager().clearPackagePreferredActivities(defaultLauncher.getPackageName());
			                    getPackageManager().addPreferredActivity(filter, defaultMatch, set, defaultLauncher);
			           }

			           //add  end
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
