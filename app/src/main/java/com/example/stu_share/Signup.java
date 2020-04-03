package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    private Button btnNext;
    private EditText txtFn,txtLn,txtQ,txtA;
    private User userReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtFn = findViewById(R.id.txtFn);
        txtLn = findViewById(R.id.txtLn);
        txtQ = findViewById(R.id.txtQst);
        txtA = findViewById(R.id.txtAns);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtFn.getText().toString().equals("") || txtLn.getText().toString().equals("") || txtQ.getText().toString().equals("") || txtA.getText().toString().equals("")
                        || txtFn.getText().toString().equals("") || txtLn.getText().toString().equals("") || txtQ.getText().toString().equals("") || txtA.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Can't leave fields empty! ",
                            Toast.LENGTH_LONG).show();
                }else if(txtFn.length()<2){
                    Toast.makeText(getBaseContext(), "First name length doesn't meet requirements! ",
                        Toast.LENGTH_LONG).show();
                }
                else if(txtLn.length()<2){
                    Toast.makeText(getBaseContext(), "Last name length doesn't meet requirements! ",
                            Toast.LENGTH_LONG).show();
                }
                else if(txtQ.length()<10){
                    Toast.makeText(getBaseContext(), "Question length doesn't meet requirements! ",
                            Toast.LENGTH_LONG).show();
                }
                else if(txtA.length()<1){
                    Toast.makeText(getBaseContext(), "Answer length doesn't meet requirements! ",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    userReg = (User) getIntent().getSerializableExtra("args");
                    userReg.setFirstName(txtFn.getText().toString());
                    userReg.setLastName(txtLn.getText().toString());
                    userReg.setQuestion(txtQ.getText().toString());
                    userReg.setAnswer(txtA.getText().toString());
                    Intent intent = new Intent(getBaseContext(), Signup2.class);
                    intent.putExtra("args", userReg);
                    startActivity(intent);
                }
            }
        });
        Button btnCanel = findViewById(R.id.btnSignupCancel);
        btnCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//              intent.putExtra("args", userReg);
                startActivity(intent);
            }
        });
    }

}
