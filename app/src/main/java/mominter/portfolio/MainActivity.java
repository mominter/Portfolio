package mominter.portfolio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private Button toastButton;
    private Button snackButton;
    private Button blueButton;
    private Button pictureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        toastButton();
        snackButton();
        blueButton();
        pictureButton();
    }

    // 토스트메시지 버튼 설정
    private void toastButton() {
        toastButton = findViewById(R.id.mainToastMessage);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToastButton();
            }
        });
    }

    // 토스트메시지 버튼 세부 설정
    private void setToastButton() {
        ToastMessageFragment Fragment = new ToastMessageFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, Fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // 스낵바 버튼 설정
    private void snackButton() {
        snackButton = findViewById(R.id.mainSnackbar);
        snackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSnackButton();
            }
        });
    }

    // 스낵바 버튼 세부 설정
    private void setSnackButton() {
        SnackbarFragment Fragment = new SnackbarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, Fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // 블루투스 버튼 설정
    private void blueButton() {
        blueButton = findViewById(R.id.mainBluetooth);
        blueButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setBlueButton();
           }
        });
    }

    // 블루투스 버튼 세부 설정
    private void setBlueButton() {
        BluetoothFragment Fragment = new BluetoothFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, Fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // 사진 버튼 설정
    private void pictureButton() {
        pictureButton = findViewById(R.id.mainPicture);
        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPictureButton();
            }
        });
    }

    // 사진 버튼 세부 설정
    private void setPictureButton() {
        PictureFragment Fragment = new PictureFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, Fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}