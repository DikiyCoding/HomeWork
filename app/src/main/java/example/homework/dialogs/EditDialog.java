package example.homework.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import example.homework.MainActivity;
import example.homework.R;

public class EditDialog extends DialogFragment {

    private EditText etLogin, etEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit, null);
        etLogin = view.findViewById(R.id.et_login);
        etEmail = view.findViewById(R.id.et_email);
        return view;
    }

    public void getInfo() {
        MainActivity.login = etLogin.getText().toString();
        MainActivity.email = etEmail.getText().toString();
    }
}