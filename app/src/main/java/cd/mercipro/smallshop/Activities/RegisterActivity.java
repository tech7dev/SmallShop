package cd.mercipro.smallshop.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import cd.mercipro.smallshop.R;

public class RegisterActivity extends AppCompatActivity {

    ImageView imgUser;
    Uri pickedImgUri;
    private FirebaseAuth mAuth;
    private EditText fullname, email, phoneNumber, password, confirmPassword;
    private Button btnCreateAccount;
    private Button btnGoLogin;
    private ProgressBar loadingProgressBar;
    static int PReqCode = 1;
    static int REQUESCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //init view
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        imgUser = findViewById(R.id.imgUser);
        btnGoLogin = findViewById(R.id.btnGoLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);

        //disable visibility
        loadingProgressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        //Go to login page
        btnGoLogin = findViewById(R.id.btnGoLogin);
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(login);
            }
        });

        //Action to create user account
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCreateAccount.setVisibility(view.INVISIBLE);
                loadingProgressBar.setVisibility(View.VISIBLE);
                final String getFullname = fullname.getText().toString();
                final String getPhoneNumber = phoneNumber.getText().toString();
                final String getEmail = email.getText().toString();
                final String getPassword = password.getText().toString();
                final String getConfirmPassword = confirmPassword.getText().toString();

                if (getFullname.isEmpty() || getPhoneNumber.isEmpty() || getEmail.isEmpty() || getPassword.isEmpty() || !getPassword.equals(getConfirmPassword)) {
                    //something goes wrong, all field must be filled
                    showMessage("SVP remplissez les champs vides");
                    btnCreateAccount.setVisibility(view.VISIBLE);
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    //everything is ok
                    CreateUserAccount(getFullname, getEmail, getPassword);
                }
            }
        });

        //On image avatar clicked
        imgUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(Build.VERSION.SDK_INT >= 22){
                    checkAndRequestForPermission();
                }
                else {
                    openGallery();
                }
            }
        });
    }

    private void CreateUserAccount(final String fullname, String email, String password) {
        //this method create user account with specific email and password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMessage("Compte a été créé");
                            //after we created user account we need to update his picture, name, phoneNumber,...
                            updateUserInfo(pickedImgUri,fullname,mAuth.getCurrentUser());

                        } else {
                            showMessage("Echec de la création du compte" + task.getException().getMessage());
                            btnCreateAccount.setVisibility(View.VISIBLE);
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
    //Update user photo, fullname and phoneNumber
    private void updateUserInfo(Uri pickedImgUri, final String fullname, final FirebaseUser currentUser) {
        //first we need to upload user photo to firebase storage and get url
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //image uploaded successfully
                //now we can get our image url
                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // uri contain user image url
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(fullname)
                                .setPhotoUri(uri)
                                .build();
                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            showMessage("Votre compte a été créé avec succès");
                                            updateUI();
                                        }
                                    }
                                });
                    }
                });
            }
        });
    }
    // we start main activity
    private void updateUI() {
        Intent loginActivity = new Intent(getApplicationContext(), cd.mercipro.smallshop.Activities.LoginActivity.class);
        startActivity(loginActivity);
        finish();
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    private void showMessage(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    // verification de la permission avant de selectionner l'image
    private void checkAndRequestForPermission() {
        if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(RegisterActivity.this,"SVP veillez autoriser l'accès", Toast.LENGTH_SHORT).show();
            }
            else{
                ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqCode);
            }
        }
        else {
            openGallery();
        }
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }

    //Image is picked successfully
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data != null){
            //the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData();
            imgUser.setImageURI(pickedImgUri);
        }
    }
}
