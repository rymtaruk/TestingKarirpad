package com.karirpad.apps.ui.Profile.Edit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.karirpad.apps.R;
import com.karirpad.apps.base.BaseActivity;
import com.karirpad.apps.databinding.ActivityEditProfileBinding;

import java.io.FileNotFoundException;

public class EditProfileActivity extends BaseActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String URL_TYPE = "URL_TYPE";
    private static final String URI_TYPE = "URI_TYPE";
    ActivityEditProfileBinding binding;
    EditProfileViewModel viewModel;
    String p2 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    String picture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = getViewModelProvider().get(EditProfileViewModel.class);
        viewModel.getUserFromDb();
        setupView();
        onViewCreated();
        observerData();
    }

    protected void setupView() {
    }

    protected void onViewCreated() {
        binding.btnChange.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                permissionAccess(p2);
            }
        });

        binding.btnSave.setOnClickListener(view -> {
            viewModel.name.setValue(binding.etName.getText().toString());
            viewModel.email.setValue(binding.etEmail.getText().toString());
            viewModel.mobile.setValue(binding.etMobile.getText().toString());
            viewModel.jobTitle.setValue(binding.etJobFunction.getText().toString());
            viewModel.expectedSalary.setValue(binding.etSalary.getText().toString());
            viewModel.bio.setValue(binding.etBio.getText().toString());
            viewModel.saveToDb();
        });
    }

    @SuppressLint("DefaultLocale")
    protected void observerData() {
        viewModel.getIsValid().observe(this, valid -> {
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        });

        viewModel.getMessageError().observe(this, message -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());

        viewModel.getResponseUserData().observe(this, data -> {

            if (data.getProfilePicture().isEmpty()) {
                binding.ivAvatar.setImageResource(R.drawable.ic_baseline_person_24);
            } else {
                picture = data.getProfilePicture();
                setupGlide(data.getProfilePicture(), null, URL_TYPE);
            }


            if (data.getPhoneNumber().isEmpty()) {
                binding.etMobile.setHint("-");
            } else {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < data.getPhoneNumber().length(); i++) {
                    if (i == 12) {
                        result.append("-");
                    } else if (i == 8) {
                        result.append("-");
                    } else if (i == 4) {
                        result.append("-");
                    }

                    result.append(data.getPhoneNumber().charAt(i));
                }
                binding.etMobile.setHint(result.toString());
            }


            if (data.getEmail().isEmpty()) {
                binding.etEmail.setHint("-");
            } else {
                binding.etEmail.setHint(data.getEmail());
            }

            if (data.getJobTitle().isEmpty()) {
                binding.etJobFunction.setHint("-");
            } else {
                binding.etJobFunction.setHint(data.getJobTitle());
            }

            if (data.getExpectedSalary().isEmpty()) {
                binding.etSalary.setHint("-");
            } else {
                binding.etSalary.setHint("IDR ".concat(String.format("%,.2f", Float.parseFloat(data.getExpectedSalary()))));
            }

            if (data.getBiodata().isEmpty()) {
                binding.etBio.setHint("-");
            } else {
                binding.etBio.setText(data.getBiodata());
            }

            if (data.getName().isEmpty()) {
                binding.etName.setHint("-");
            } else {
                binding.etName.setHint(data.getName());
            }

        });
    }

    private void permissionAccess(String p) {
        if (checkPermission(p)) {
            requestPermission(p);
        } else {
            if (p.equalsIgnoreCase(p2)) {
                selectImage();
            }
        }
    }

    private boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
        return result != PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(String permission) {
        ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{permission}, PERMISSION_REQUEST_CODE);
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() != RESULT_CANCELED) {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri selectedImage = result.getData().getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                if (selectedImage != null) {
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        setupGlide(picturePath, selectedImage, URI_TYPE);
                        cursor.close();
                    }
                }
            }
        }
    });

    private void selectImage() {
        final CharSequence[] options = {"Select From Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("Change Picture");

        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Select From Gallery")) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                launcher.launch(pickPhoto);
            } else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equalsIgnoreCase(p2)) {
                    selectImage();
                }
            }
        }
    }

    private void setupGlide(String name, Uri uri, String type) {
        if (type.equalsIgnoreCase(URL_TYPE)) {
            Glide.with(getApplicationContext())
                    .load(name)
                    .placeholder(R.drawable.bg_dsb_shimer_rounded)
                    .centerCrop()
                    .circleCrop()
                    .error(R.drawable.ic_baseline_person_24)
                    .into(binding.ivAvatar);
        } else {
            Bitmap bitmap = null;
            String uriString = uri.toString();
            viewModel.picture.setValue(uriString);
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                Log.e("IMAGE decodeStream : ", e.getLocalizedMessage());
            }
            Glide.with(getApplicationContext())
                    .load(bitmap)
                    .placeholder(R.drawable.bg_dsb_shimer_rounded)
                    .centerCrop()
                    .circleCrop()
                    .into(binding.ivAvatar);
        }
    }
}