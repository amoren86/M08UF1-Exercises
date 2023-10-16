package cat.institutmarianao.name.or.age;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnterNameOrAgeActivity extends AppCompatActivity {

    private EditText input;
    private int option;
    private String textOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name_or_age);

        Intent mainActivityIntent = getIntent();
        option = mainActivityIntent.getExtras().getInt("option");

        int id;
        switch (option) {
            case MainActivity.NAME_ID:
                id = R.id.etName;
                textOption = "name";
                break;
            case MainActivity.AGE_ID:
                id = R.id.etAge;
                textOption = "age";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }

        input = findViewById(id);
        input.setVisibility(View.VISIBLE);

        TextView tvMessage = findViewById(R.id.tvMessage);
        tvMessage.setText("Please, enter your " + textOption);
    }

    public void buttonSubmitClick(View view) {
        if (TextUtils.isEmpty(input.getText())) {
            Toast.makeText(this, "The " + textOption + " can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        String answer = input.getText().toString().trim();

        Intent mainActivityIntent = getIntent();
        switch (option) {
            case MainActivity.NAME_ID:
                mainActivityIntent.putExtra("name", answer);
                break;
            case MainActivity.AGE_ID:
                mainActivityIntent.putExtra("age", Integer.parseInt(answer));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
        setResult(RESULT_OK,mainActivityIntent);
        finish();
    }

    public void buttonCancelClick(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}