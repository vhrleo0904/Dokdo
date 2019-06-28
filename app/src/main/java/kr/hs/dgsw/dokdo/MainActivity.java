package kr.hs.dgsw.dokdo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private EditText editTextSearch;
    private Button buttonIntro;
    private Button buttonWay;
    private Button buttonNature;
    private Button buttonHistory;

    private Intent intent;

    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = findViewById(R.id.editTextSearch);

        helper = new DBHelper(this, "userdb", null, 9);
    }

    public void onImageButton(View v) {
        Log.d("main", "onImageButton 실행");

        String text = "내용을 입력해주세요.";
        if(editTextSearch.getText().toString().equals("")) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("main", "Main to Search");

        text = editTextSearch.getText().toString();
        intent = new Intent(this, SearchActivity.class);
        intent.putExtra("text", text);
        startActivity(intent);
    }

    public void onButton(View v) {
        Intent intent = new Intent(this, TabActivity.class);
        TabBean tab = helper.get(((Button)v).getText().toString().replace("\n", " ")).get(0);
        int tab_id = tab.getId();
        String tab_title = tab.getTitle();
        String tab_content = tab.getContent();
        intent.putExtra("id", tab_id);
        intent.putExtra("title", tab_title);
        intent.putExtra("content", tab_content);
        startActivity(intent);
    }
}
