package cat.institutmarianao.name.or.age;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int NAME_ID = 0;
    public static final int AGE_ID = 1;

    private TextView tvAnswer;
    private Intent enterNameOrAgeIntent;
    private ActivityResultLauncher<Intent> enterNameLauncher;
    private ActivityResultLauncher<Intent> enterAgeLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAnswer = findViewById(R.id.tvAnswer);
        enterNameOrAgeIntent = new Intent(this, EnterNameOrAgeActivity.class);

        enterNameLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            String answer = "";
            if (result.getResultCode() == Activity.RESULT_OK) {
                // There are no request codes
                Intent data = result.getData();
                String name = data.getExtras().getString("name");
                answer = "The name entered is " + name;
            }
            tvAnswer.setText(answer);
        });

        enterAgeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            String answer = "";
            if (result.getResultCode() == Activity.RESULT_OK) {
                // There are no request codes
                Intent data = result.getData();
                int age=data.getExtras().getInt("age");
                answer = "The age entered is " + age;
            }
            tvAnswer.setText(answer);
        });
    }

    public void onNameClick(View view) {
        enterNameOrAgeIntent.putExtra("option", NAME_ID);
        enterNameLauncher.launch(enterNameOrAgeIntent);
    }

    public void onAgeClick(View view) {
        enterNameOrAgeIntent.putExtra("option", AGE_ID);
        enterAgeLauncher.launch(enterNameOrAgeIntent);
    }
}