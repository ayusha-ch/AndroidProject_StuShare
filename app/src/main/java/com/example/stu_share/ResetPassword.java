package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class ResetPassword extends AppCompatActivity {
    Button btnSubmit,btnCancel;
    TextView txtQ;
    EditText txtA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        txtA=findViewById(R.id.editTextAnswer);
        txtQ=findViewById(R.id.txtQ);
        final User userPR=(User)getIntent().getSerializableExtra("args");
        btnCancel=findViewById(R.id.btnPRCancel);
        btnSubmit=findViewById(R.id.btnPRSubmit);
        txtQ.setText(userPR.question);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtA.getText().toString().equals(userPR.answer)){
                    Intent intent=new Intent (getBaseContext(),ResetConfirm.class);
                    intent.putExtra("args",userPR);
                    startActivity(intent);
                }else{
                    txtA.setText("wrong answer, please try again");
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                intent.putExtra("user",userPR);
                startActivity(intent);
            }
        });

    }
}
