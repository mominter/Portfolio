package mominter.portfolio;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class BluetoothFragment extends Fragment {
    private ActivityResultLauncher<Intent> bluetoothEnableLauncher;
    private ActivityResultLauncher<String[]> permissionRequestLauncher;

    private View bluetoothView;
    private Button bluetoothButton;
    private Button backPressButton;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // 블루투스 활성화 런처 초기화
        bluetoothEnableLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Toast.makeText(requireContext(), "블루투스가 활성화되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "블루투스 활성화를 취소했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // 권한 요청 런처 초기화
        permissionRequestLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    Boolean isGranted = result.getOrDefault(Manifest.permission.BLUETOOTH_CONNECT, false);
                    if (isGranted) {
                        Toast.makeText(requireContext(), "권한이 부여되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "블루투스 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bluetoothView = inflater.inflate(R.layout.bluetooth, container, false);

        bluetoothButton();
        backPressButton();

        return bluetoothView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // 블루투스 버튼 설정
    private void bluetoothButton() {
        bluetoothButton = bluetoothView.findViewById(R.id.subBluetooth);
        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBluetoothButton();
            }
        });
    }

    // 뒤로가기 버튼 설정
    private void backPressButton() {
        backPressButton = bluetoothView.findViewById(R.id.subBlueBackPress);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    // 블루투스 버튼 누를 때의 설정
    private void setBluetoothButton() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // 블루투스 지원 여부 확인
        if (bluetoothAdapter == null) {
            Toast.makeText(requireContext(), "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 권한 확인
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // 권한 요청
            requestBluetoothPermission();
            return;
        }

        // 블루투스 상태에 따라 켜거나 끔
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable(); // 블루투스 끄기
            // 보안 정책에 따라 앱 화면에서 비활성화는 불가능
            // 사용자가 직접 설정 화면으로 가서 블루투스를 비활성화를 실행
            Toast.makeText(requireContext(), "블루투스 비활성화 기능을 위해 설정창으로 이동합니다", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(intent);
        } else {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            bluetoothEnableLauncher.launch(enableBtIntent); // 블루투스 켜기
        }
    }

    // 블루투스 권한 설정
    private void requestBluetoothPermission() {
        permissionRequestLauncher.launch(new String[]{Manifest.permission.BLUETOOTH_CONNECT});
    }
}
