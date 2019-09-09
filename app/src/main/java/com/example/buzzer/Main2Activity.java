package com.example.buzzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {
    private static final Pattern passwordPattern=
            Pattern.compile("^" +
                   // "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
     private EditText username,reenter,firstname,password;
     private RadioGroup rg;
     private Button btn1;
     private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth = FirebaseAuth.getInstance();
        btn1 = findViewById(R.id.button);
        username = findViewById(R.id.username);
        firstname = findViewById(R.id.firstname);
        reenter = findViewById(R.id.reenterpassword);
        rg = findViewById(R.id.radiogroup);
        password = findViewById(R.id.password);

        final String uname = username.getText().toString();
        String fname = firstname.getText().toString();
        String reente = reenter.getText().toString();
        final String pname = password.getText().toString();



            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    if (validatepassword()&&emailValidate()&&validaterepasword()) {
                        String uname = username.getText().toString();
                        String pname = password.getText().toString();
                        firebaseAuth.createUserWithEmailAndPassword(uname, pname).addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Main2Activity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                    Log.d("Fire", "createUserWithEmail:success");
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else
                                    Toast.makeText(Main2Activity.this, "Registration InComplete", Toast.LENGTH_SHORT).show();
                                Log.w("ei", "createUserWithEmail:failure", task.getException());
                            }
                        });
                    }



                }
            });
        }



public boolean validatepassword() {
    String reente = reenter.getText().toString();

    if (reente.isEmpty()) {
        reenter.setError("CAnt be Empty");
        return false;

    } else if (!passwordPattern.matcher(reente).matches()) {
        reenter.setError("Weak Password");
        return false;
    }
    return true;

}
public boolean validaterepasword()
{
    String pname = password.getText().toString();

      if (pname.isEmpty()) {
            password.setError("CAnt be Empty");
            return false;

        }
        else if (!passwordPattern.matcher(pname).matches()){
            password.setError("Weak Password");
            return false;
        }

      else
          return true;



}
 public boolean emailValidate()
 {
     String uname = username.getText().toString();
     if(uname.isEmpty())
     {
         username.setError("Can't Be Empty");
         return false;
     }

       else if (!Patterns.EMAIL_ADDRESS.matcher(uname).matches())
     {
         username.setError("Please Enter Valid Email");
         return false;
     }
       else
           return true;
 }


 /* public boolean validate()
  {


      String pname = password.getText().toString();

      if (TextUtils.isEmpty(uname)||TextUtils.isEmpty(pname))
        {
            Toast.makeText(Main2Activity.this, "Enter required Field", Toast.LENGTH_SHORT).show();

            return false;
        }
*/

  }






