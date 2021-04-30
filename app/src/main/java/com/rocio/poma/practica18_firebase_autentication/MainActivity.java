package com.rocio.poma.practica18_firebase_autentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth myAuth;
    int INICIAR_CON_FBUI=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAuth=FirebaseAuth.getInstance();
        EditText editTextEmail=findViewById(R.id.editTextEmail);
        EditText editTextClave=findViewById(R.id.editTextClave);
        Button btnAcceder=findViewById(R.id.btnAcceder);

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editTextEmail.getText().toString();
                String clave=editTextClave.getText().toString();
                myAuth.signInWithEmailAndPassword(email,clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent inte=new Intent( MainActivity.this,MainActivity2.class);
                            startActivity(inte);
                        }else {
                            Toast.makeText(getApplicationContext(),"Usuario/clave incorrecto",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button btnLogin2= findViewById(R.id.btnFirebaseUI);
        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<AuthUI .IdpConfig> providers =Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build()
                    //new AuthUI.IdpConfig.FacebookBuilder().build(),
                   // new  AuthUI.IdpConfig.MicrosoftBuilder().build()
                );
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder().build(),INICIAR_CON_FBUI
            );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==INICIAR_CON_FBUI&&resultCode==RESULT_OK){
            if (myAuth.getCurrentUser()!=null){
                Intent inte=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(inte);
            }
        }
    }
}