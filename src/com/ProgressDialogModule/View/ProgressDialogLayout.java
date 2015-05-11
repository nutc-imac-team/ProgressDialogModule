package com.ProgressDialogModule.View;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ProgressDialogLayout extends RelativeLayout{
	private Context context;
	private Button buttonTest;
	public ProgressDialogLayout(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init(){
		setView();
	}
	
	private void setView(){
		buttonTest = new Button(context);
		LayoutParams layoutParams = new LayoutParams(500, 500);
		buttonTest.setLayoutParams(layoutParams);
		buttonTest.setText("測試");
		this.addView(buttonTest);
	}
	
	public Button getButton(){
		return this.buttonTest;
	}
}
