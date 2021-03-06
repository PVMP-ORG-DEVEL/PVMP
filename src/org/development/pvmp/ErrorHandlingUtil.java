package org.development.pvmp;

import android.content.Context;
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
	
	public static void genericError (EditText editText, CharSequence text, Context context) {
		if (editText != null) {
			showToast(text, context);
			requestAttention (editText);
		}
	}
	
	public static void displayEditError (EditText et1, EditText et2, EditText et3,
										 EditText et4, int validationResult,
										 Context context) {
		switch (validationResult) {
			case 1:
				ErrorHandlingUtil.genericError(et2, "Nome inv�lido.", context);
				break;
			case 2:
				ErrorHandlingUtil.genericError(et2, "Seu nome deve ter de 3 a 50 caracteres.", context);
				break;
			case 3:
				ErrorHandlingUtil.genericError(et1, "Formato de email inv�lido.", context);
				break;
			case 4:				
				ErrorHandlingUtil.genericError(et1, "Seu email deve ter, no m�ximo, 40 caracteres.", context);
				break;
			case 5:
				ErrorHandlingUtil.genericError(et4, "Sua idade tem de estar entre o intervalo 10-99.", context);
				break;
			case 6:
				ErrorHandlingUtil.genericError(et3, "Sua senha deve ter de 6 a 15 caracteres.", context);
				break;
			case 7:
				ErrorHandlingUtil.genericError(et3, "Sua senha pode ser formada apenas por letras e n�meros.", context);
				break;
		}
	}
	
	public static void displayRegisterError(EditText et1, EditText et2, EditText et3,
										    EditText et4, EditText et5, int validationResult,
										    Context context) {
	    displayEditError(et2, et3, et4, et5, validationResult, context);
	    if (validationResult > 6) {
			switch (validationResult) {
				case 7:
					ErrorHandlingUtil.genericError(et2, "Email j� existente.", context);
					break;
				case 8:
					ErrorHandlingUtil.genericError(et1, "Nome de usu�rio j� existente.", context);
					break;
				case 9:
					ErrorHandlingUtil.genericError(et1, "Seu nome de usu�rio deve ter de 3 a 15 caracteres.", context);
					break;
				case 10:
					ErrorHandlingUtil.genericError(et1, "Seu nome de usu�rio deve come�ar com uma letra.", context);
					break;
				case 11:
					ErrorHandlingUtil.genericError(et1, "Seu nome de usu�rio deve ser composto apenas de letras e n�meros.", context);
					break;
		    }
	    }
	}
}
