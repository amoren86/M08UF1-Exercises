package cat.institutmarianao.name.or.age;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int NAME_ID = 0;
    public static final int AGE_ID = 1;

    private TextView tvAnswer;
    private Intent enterNameOrAgeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAnswer=findViewById(R.id.tvAnswer);
        enterNameOrAgeIntent = new Intent(this, EnterNameOrAgeActivity.class);
    }

    public void onNameClick(View view) {
        enterNameOrAgeIntent.putExtra("option", NAME_ID);
        startActivityForResult(enterNameOrAgeIntent, NAME_ID);
    }

    public void onAgeClick(View view) {
        enterNameOrAgeIntent.putExtra("option", AGE_ID);
        startActivityForResult(enterNameOrAgeIntent, AGE_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            tvAnswer.setText("");
            return;
        }
        String answer;
        switch (requestCode) {
            case NAME_ID:
                String name=data.getExtras().getString("name");
                answer="The name entered is "+name;
                break;
            case AGE_ID:
                int age=data.getExtras().getInt("age");
                answer="The age entered is "+age;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
        tvAnswer.setText(answer);
    }
}