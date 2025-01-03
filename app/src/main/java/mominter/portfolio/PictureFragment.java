package mominter.portfolio;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

public class PictureFragment extends Fragment {
    private View pictureView;
    private Button pictureButton;
    private Button backPressButton;
    private ActivityResultLauncher<Intent> galleryLauncher;

    final int GET_GALLERY_IMAGE = 200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pictureView = inflater.inflate(R.layout.picture, container, false);

        pictureButton();
        backPressButton();

        return pictureView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        handleSelectedImage(selectedImageUri);
                    }
                }
        );*/
    }

    // 이미지 버튼 설정
    private void pictureButton() {
        pictureButton = pictureView.findViewById(R.id.subPicture);
        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPictureButton();
            }
        });
    }

    // 뒤로가기 버튼 설정
    private void backPressButton() {
        backPressButton = pictureView.findViewById(R.id.subPictureBackPress);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    // 이미지 세부내용 설정
    private void setPictureButton() {
        /*Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        galleryLauncher.launch(intent);*/

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivity(intent);
    }
}
