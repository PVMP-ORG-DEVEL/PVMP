package org.development.pvmp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

public class ErrorHandlingUtil {
	public static void showToast (CharSequence text, Context context) {
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	
	public static void requestAttention (EditText editText) {
		editText.setText("");
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
	}
	
	public static void genericError (@Nullable EditText editText, CharSequence text, Context context, boolean wantRequest) {
		showToast(text, context);
		if (wantRequest)
			requestAttention (editText);
	}
	
}
