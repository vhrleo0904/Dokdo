package kr.hs.dgsw.dokdo;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TabAdapter extends RecyclerView.Adapter<TabItemViewHolder> {

    private ArrayList<TabBean> tabData;
    private ItemClickListener listener;

    public TabAdapter(ArrayList<TabBean> data, ItemClickListener listener) {
        this.tabData = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TabItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_tab, viewGroup, false);
        return new TabItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TabItemViewHolder viewHolder, int i) {
        TabBean post = tabData.get(i);
        String title = post.getTitle();
        String content = post.getContent();
        viewHolder.textViewTitle.setText(title);
        if(content.length() > 27) {
            content = content.substring(0, 25) + "...";
        }
        viewHolder.textViewContent.setText(content);

        viewHolder.itemView.setOnClickListener(v -> {
            listener.onItemClick(v, i);
        });
    }

    @Override
    public int getItemCount() {
        if(tabData == null)
            return 0;
        else
            return tabData.size();
    }
}
