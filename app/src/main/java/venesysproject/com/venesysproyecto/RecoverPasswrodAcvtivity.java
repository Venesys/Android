package venesysproject.com.venesysproyecto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class RecoverPasswrodAcvtivity extends AppCompatActivity implements View.OnClickListener {

    private EditText e1;
    private Button b1;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    /*private FirebaseAuth.AuthStateListener mAuthListener;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password);

        assingComponents();
        listener();
    }

    private void assingComponents() {
        e1 = (EditText) findViewById(R.id.editText3);
        b1 = (Button) findViewById(R.id.button7);
    }

    private void listener() {
        b1.setOnClickListener(this);
    }

    private void recoveryPassword(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RecoverPasswrodAcvtivity.this, "Se ha enviado un correo de recuperacion a su cuenta", Toast.LENGTH_LONG).show();
                    Log.d("recovery", "Email sent.");
                } else {

                    Log.i("recovery","ERROR "+task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==findViewById(R.id.button7).getId()) {
            String email;
            email = e1.getText().toString();
            recoveryPassword(email);
        }
    }
}
