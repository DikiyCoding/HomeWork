package example.homework.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import example.homework.MainActivity;
import example.homework.R;
import example.homework.dialogs.EditDialog;

public class ProfileFragment extends Fragment {

    private View view;
    private TextView tvLogin, tvEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, null);
        tvLogin = view.findViewById(R.id.tv_login);
        tvEmail = view.findViewById(R.id.tv_email);
        return view;
    }

    public void setInfo(String login, String email) {
        tvLogin.setText(login);
        tvEmail.setText(email);
    }
}
