package com.example.buzzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.view.View.OnClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
  private String Name;
  private Button button,button2;
  private EditText editText,editText1;
  private FirebaseAuth firebaseAuth;
  private ProgressBar progressBar;


       public void getMessage(String string)
       {
           Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
       }

       public void validate(String username,String password)
       {

            progressBar.isIndeterminate();

           firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {

                   if(task.isSuccessful()){
                       finish();
                       startActivity(new Intent(MainActivity.this,Main2Activity.class));
                      getMessage("Login sucessfull");
                   }
                   else
                     getMessage("Login Unsucessful!");

               }
           });
       }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=new ProgressBar(MainActivity.this);

         editText =findViewById(R.id.editText);
        editText1=findViewById(R.id.editText2);

         button=findViewById(R.id.button);
         button2=findViewById(R.id.new_button);
         firebaseAuth=FirebaseAuth.getInstance();



         FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if (firebaseUser==null)
         {
             finish();
             startActivity(new Intent(MainActivity.this,Main2Activity.class));
         }

        button2.setOnClickListener(view1 -> {
            Log.i("button2","Hello");
            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(intent);

        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=editText.getText().toString();
                String password=editText1.getText().toString();

                validate(username,password);




    }
});
    }
}