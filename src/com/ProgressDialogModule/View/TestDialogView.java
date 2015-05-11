package com.ProgressDialogModule.View;

import com.ProgressDialogModule.Module.ProgressDialogModuleView;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

public class TestDialogView extends RelativeLayout{
	private Context context;
	private ProgressDialogModuleView dialogModuleView;
	public TestDialogView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init(){
		setView();
	}
	
	private void setView(){
		dialogModuleView = new ProgressDialogModuleView(context);
		LayoutParams layoutParams = new LayoutParams(300, 300);
		layoutParams.addRule(CENTER_IN_PARENT);
		dialogModuleView.setLayoutParams(layoutParams);
		dialogModuleView.setFinishColor(0xFF00BBFF);
		dialogModuleView.setUnfinishColor(0xFFCCCCFF);
		dialogModuleView.setCircleBackgroundColor(Color.BLACK);
		dialogModuleView.setTextColor(Color.WHITE);
		this.addView(dialogModuleView);
	}
	
	public ProgressDialogModuleView getDialogModuleView(){
		return this.dialogModuleView;
	}
}
