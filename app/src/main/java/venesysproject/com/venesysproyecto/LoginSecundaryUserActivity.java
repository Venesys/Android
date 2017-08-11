package venesysproject.com.venesysproyecto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginSecundaryUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText e1;
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
        b1 = (Button) findViewById(R.id.button8);
    }

    private void listener() {
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==findViewById(R.id.button8).getId()) {
            String placa = "123qwe";
            String clave1 = e1.getText().toString();
            myRef.child("auto").child("placa").child("clave");
            //myRef.addListenerForSingleValueEvent(new );
        }
    }
}
