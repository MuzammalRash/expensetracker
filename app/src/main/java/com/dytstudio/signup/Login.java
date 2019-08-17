package com.dytstudio.signup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private ProgressDialog progressDialog;
    private Button btnSignIn;
    private EditText email,password;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView signupText=(TextView)findViewById(R.id.Open_sign_up);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        btnSignIn=(Button)findViewById(R.id.btn_signin);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
//        if(user != null) {
//            finish();
//            startActivity(new Intent(Login.this,Dashboard.class));
//        }
//

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (email.getText().length() == 0) {
                    email.setError( "Enter email" );
                } else if (password.getText().length() == 0) {
                    password.setError( "Enter password" );
                } else {
                    login();
                }

            }
        });


        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });
    }

    public void login() {

        progressDialog.setMessage( "Verificating..." );
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword( email.getText().toString(), password.getText().toString() )
                .addOnCompleteListener( Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this,"Invalid email or password",Toast.LENGTH_SHORT).show();
                        } else {
                            String  id = task.getResult().getUser().getUid();


                            SharedPreferencesManager.setSomeStringValue(getApplication(),id);
                            progressDialog.dismiss();
                            Intent intent = new Intent( Login.this, Dashboard.class );
                            startActivity( intent );
                        }
                    }
                } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText( Login.this, "" + e.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        password.setText("");
        email.setText("");

    }

}
