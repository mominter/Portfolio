package mominter.portfolio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarFragment extends Fragment {
    private View snackbarView;
    private Button snackbarButton;
    private Button backPressButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        snackbarView = inflater.inflate(R.layout.snackbar, container, false);

        snackbarButton();
        backPressButton();

        return snackbarView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // 스낵바 버튼 설정
    private void snackbarButton() {
        snackbarButton = snackbarView.findViewById(R.id.subSnackbar);
        snackbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSnackbarButton();
            }
        });
    }

    // 뒤로가기 버튼 설정
    private void backPressButton() {
        backPressButton = snackbarView.findViewById(R.id.subSnackBackPress);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    // 스낵바 세부내용 설정
    private void setSnackbarButton() {
        Snackbar snackbar = Snackbar.make(snackbarView, "", Snackbar.LENGTH_LONG);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);

        LayoutInflater inflater = LayoutInflater.from(snackbarLayout.getContext());
        View customSnackView = inflater.inflate(R.layout.custom_snackbar, null);

        snackbarLayout.addView(customSnackView, 0);
        snackbar.show();
    }
}
