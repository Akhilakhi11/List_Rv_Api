package com.example.list_rv_api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Person;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.internal.ws.RealWebSocket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity4 extends AppCompatActivity {

    @BindView(R.id.nametv)
    TextView nametv;
    @BindView(R.id.jobtv)
    TextView jobtv;
    @BindView(R.id.nameed)
    EditText nameed;
    @BindView(R.id.jobed)
    EditText jobed;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.imgpic)
    CircleImageView imgpic;
    @BindView(R.id.fabedit)
    FloatingActionButton fabedit;
    Dialog dialog;

    private int STORAGE_PERMISSION_CODE = 5;
    private static final int CAMERA_PERMISSION_CODE = 1;
    private static final int GALLERY_PERMISSION_CODE = 2;
    private static final int CAMERA_REQUEST = 3;
    private static final int GALLERY_REQUEST = 4;
    private Person result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        ButterKnife.bind(this);

        fabedit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Activity4.this);
                dialog.setContentView(R.layout.dialog_design);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);


                TextView camera = dialog.findViewById(R.id.camicon);
                camera.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View view) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                        } else {
                            Intent snap = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(snap, CAMERA_REQUEST);
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();

                TextView gallery = dialog.findViewById(R.id.galleryicon);
                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_PERMISSION_CODE);
                        } else {
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, GALLERY_REQUEST);
                            dialog.dismiss();
                        }
                    }

                });
                dialog.show();
            }
        });
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(Activity4.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK && resultCode == Activity4.RESULT_OK) {
                if (requestCode == CAMERA_REQUEST){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imgpic.setImageBitmap(photo);
                }else if (requestCode == GALLERY_REQUEST){
                        try {
                            Uri uri = data.getData();
                            imgpic.setImageURI(uri);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }



    @OnClick(R.id.login)
    public void onCreateBtnPressed() {
        UserRequest user = new UserRequest();
        user.setName(nameed.getText().toString());
        user.setJob(jobed.getText().toString());

        Interface_Api methods = RetrofitClient.getRetrofitInstance().create(Interface_Api.class);
        Call<UserResponse> call = methods.postUser(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Activity4.this, response.body().getId(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Activity4.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
        }
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
        }
    }
}







