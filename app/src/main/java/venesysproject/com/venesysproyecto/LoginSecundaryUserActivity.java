package venesysproject.com.venesysproyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginSecundaryUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText e1, e2;
    private Button b1;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("auto");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_secundary_user);

        assingComponents();
        listener();
    }

    private void assingComponents() {
        e1 = (EditText) findViewById(R.id.editText4);
        e2 = (EditText) findViewById(R.id.editText5);
        b1 = (Button) findViewById(R.id.button8);
    }

    private void listener() {
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==findViewById(R.id.button8).getId()) {
            final String clave1 = e1.getText().toString();
            final String placa1 = e2.getText().toString();

            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean flag = false;
                    boolean val1 = dataSnapshot.child(placa1).exists();

                    if(val1) {
                        Object clave2 = dataSnapshot.child(placa1).child("clave_permiso").getValue();

                        if(clave1.equals(clave2.toString())) {
                            Intent intent = new Intent(LoginSecundaryUserActivity.this, HomeSecundaryUserActivity.class);
                            startActivity(intent);
                            flag = true;
                            //private void savesession() {
                                SessionSecundaryUser app = (SessionSecundaryUser) getApplication();
                                if (app !=null){
                                    User user = new User();
                                    user.setPlaca(placa1);
                                    app.setUser(user);

                                    //User user1 = app.getUser();
                                }
                            //}
                        }
                    }

                    if(flag==false) {
                        Toast.makeText(LoginSecundaryUserActivity.this, "Datos incorrectos", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("secundary", "ERROR "+databaseError.toException());
                }
            };
            myRef.addListenerForSingleValueEvent(listener);
        }
    }
}
