package kr.hs.dgsw.dokdo;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TabActivity extends AppCompatActivity {

    private Intent intent;
    private TextView textViewTitle;
    private TextView textViewContent;

    private ImageAdapter imageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewContent = findViewById(R.id.textViewContent);
        viewPager = findViewById(R.id.viewPager);

        intent = getIntent();

        int id = intent.getIntExtra("id", 0);

        imageAdapter = new ImageAdapter(this, id);
        viewPager.setAdapter(imageAdapter);

        String tab_title = intent.getStringExtra("title");
        String tab_content = intent.getStringExtra("content");

        textViewTitle.setText(tab_title);
        textViewContent.setText(tab_content);
    }

    public void onTextView(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("https://ko.wikipedia.org/wiki/%EB%8F%85%EB%8F%84"));

        startActivity(intent);
    }
}
