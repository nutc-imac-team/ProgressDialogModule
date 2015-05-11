package com.DialogModule;

import com.ProgressDialogModule.View.ProgressDialogLayout;
import com.ProgressDialogModule.View.TestDialogView;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	private ProgressDialogLayout layout;

	private TestDialogView dialogView;
	private Handler handler;
	private AlertDialog AD;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout = new ProgressDialogLayout(this));

		layout.getButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				handler = new Handler();
				handler.post(runProgress);
				AD = new AlertDialog.Builder(MainActivity.this).setView(
						dialogView = new TestDialogView(MainActivity.this))
						.show();
			}
		});
	}

	private Runnable runProgress = new Runnable() {
		public void run() {
			int progress = dialogView.getDialogModuleView().getProgress();
			dialogView.getDialogModuleView().setProgress(progress + 1);
			if (dialogView.getDialogModuleView().getProgress() >= dialogView
					.getDialogModuleView().getMax()) {
				AD.dismiss();
				handler.removeCallbacks(runProgress);
				handler = null;
			}
			if (handler != null) {
				handler.postDelayed(runProgress, 100);
			}
		}
	};
}
