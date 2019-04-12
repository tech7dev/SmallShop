package cd.mercipro.smallshop.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import cd.mercipro.smallshop.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnGoRegister;
    private EditText txtEmail;
    private EditText txtPassword;
    private ProgressBar loginProgress;
    private  Intent MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //init view
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        loginProgress = findViewById(R.id.loginProgress);
        MainActivity = new Intent(this, MainActivity.class);

        loginProgress.setVisibility(View.INVISIBLE);

        //Go to registerActivity
        btnGoRegister = findViewById(R.id.btnGoRegister);
        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(register);
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loginProgress.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);
                final String email = txtEmail.getText().toString();
                final String password = txtPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()){
                    showMessage("SVP Verifier les champs vides");
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else{
                    //signIn(email,password);
                }
            }
        });
    }

    private void signIn(String email, String password) {

    }

    private void updateUI() {
        startActivity(MainActivity);
        finish();
    }

    private void showMessage(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}
