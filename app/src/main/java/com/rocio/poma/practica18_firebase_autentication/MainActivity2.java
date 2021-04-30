package com.rocio.poma.practica18_firebase_autentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView txtDatos=findViewById(R.id.txtDatos);
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null){
            FirebaseUser fbUser=mAuth.getCurrentUser();
            txtDatos.setText(fbUser.getEmail());
        }

        Button btnCerrarSesion=findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent inte=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(inte);
            }
        });
    }
}