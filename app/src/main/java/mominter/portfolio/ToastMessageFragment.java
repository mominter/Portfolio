package mominter.portfolio;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class ToastMessageFragment extends Fragment {
    private View toastMessageView;
    private Button toastButton;
    private Button backPressButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toastMessageView = inflater.inflate(R.layout.toast_message, container, false);

        toastButton();
        backPressButton();

        return toastMessageView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    // 토스트메시지 버튼 설정
    private void toastButton() {
        toastButton = toastMessageView.findViewById(R.id.subToastMessage);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToastButton();
            }
        });
    }


    // 뒤로가기 버튼 설정
    private void backPressButton() {
        backPressButton = toastMessageView.findViewById(R.id.subToastBackPress);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }


    // 토스트메시지 세부사항 설정
    private void setToastButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View toastView = inflater.inflate(R.layout.custom_toast_message, null);
        ImageView Image = toastView.findViewById(R.id.toastImage);
        TextView Title = toastView.findViewById(R.id.toastTitle);
        TextView Content = toastView.findViewById(R.id.toastContent);

        Image.setImageResource(R.drawable.info_square_fill);
        Image.setColorFilter(ContextCompat.getColor(toastView.getContext(), R.color.info));
        Title.setText("알림 타이틀 설정");
        Content.setText("알림 콘텐츠 설정");

        builder.setView(toastView);
        AlertDialog dialog = builder.create();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setDimAmount(0);

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.TOP;
        dialog.getWindow().setAttributes(params);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();


        // 핸들러 설정 (LENGTH_LONG으로 1초간 띄우기)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, Toast.LENGTH_LONG + 1000);
    }
}
