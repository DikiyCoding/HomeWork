package example.homework.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.homework.R;

public class Page extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "page_number";

    private TextView tvPage;
    private View view;

    private int pageNumber;
    private String[] text;

    public static Page newInstance(int page) {
        Page pageFragment = new Page();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        text = getResources().getStringArray(R.array.tv_page_array);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_page, null);
        tvPage = view.findViewById(R.id.tv_page);
        tvPage.setText(text[pageNumber]);
        return view;
    }
}
