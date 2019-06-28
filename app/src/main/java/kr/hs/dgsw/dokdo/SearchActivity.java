package kr.hs.dgsw.dokdo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView tabList;
    private DBHelper helper;
    private TabAdapter adapter;
    private ArrayList<TabBean> data;

    private EditText editTextResearch;
    private String search;
    private TextView textViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextResearch = findViewById(R.id.editTextResearch);

        tabList = findViewById(R.id.tabList);

        search = getIntent().getStringExtra("text");
        editTextResearch.setText(search);

        helper = new DBHelper(this, "userdb", null, 9);

        textViewNews = findViewById(R.id.textViewNews);

        refreshRecyclerview();
    }

    public void onImageButton(View v) {
        search = editTextResearch.getText().toString();

        refreshRecyclerview();
    }

    public void refreshRecyclerview() {
        //helper.onCreate();
        //data = helper.getAll();
        data = helper.get(search);
        Log.d("searchActivity", "data.size() = " + data.size());

        if(data.size() == 0) {
            Toast.makeText(this, "검색한 결과가 없습니다.", Toast.LENGTH_SHORT).show();

            textViewNews.setVisibility(View.VISIBLE);
        }
        else {
            textViewNews.setVisibility(View.INVISIBLE);
        }

        adapter = new TabAdapter(data, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        tabList.setLayoutManager(manager);
        tabList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, TabActivity.class);
        Log.d("[Search]", "position : " + position);
        TabBean tab = data.get(position);
        int id = tab.getId();
        String title = tab.getTitle();
        String content = tab.getContent();

        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        startActivity(intent);
    }
}
