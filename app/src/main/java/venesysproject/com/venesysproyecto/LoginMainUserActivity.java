package venesysproject.com.venesysproyecto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginMainUserActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView t1, t2;
    private EditText e1, e2;
    private Button b1;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main_user);

        assingComponents();
        listener();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null) {
                    Intent intent = new Intent(LoginMainUserActivity.this, HomeMainUserActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Log.i("session", "usuario tiene una sesion:" + user.getUid());
                } else {
                    Log.i("session", "usuario no tiene sesion");
                }
            }
        };
    }

    private void assingComponents() {
        t1 = (TextView) findViewById(R.id.textView10);
        t2 = (TextView) findViewById(R.id.textView11);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button);
    }

    private void listener() {
        b1.setOnClickListener(this);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(LoginMainUserActivity.this, HomeMainUserActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginMainUserActivity.this, "Datos incorrectos", Toast.LENGTH_LONG).show();
                    Log.i("session","ERROR "+task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==findViewById(R.id.button).getId()) {
            String user, password;
            user = e1.getText().toString();
            password = e2.getText().toString();
            signIn(user, password);
        }
            else if(v.getId()==findViewById(R.id.textView10).getId()) {
                Intent intent = new Intent(LoginMainUserActivity.this, LoginSecundaryUserActivity.class);
                startActivity(intent);
            }
                else if(v.getId()==findViewById(R.id.textView11).getId()) {
                    //mAuth.signOut();
                    Intent intent = new Intent(LoginMainUserActivity.this, RecoverPasswrodAcvtivity.class);
                    startActivity(intent);
                }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}