package kr.hs.dgsw.dokdo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TabItemViewHolder extends RecyclerView.ViewHolder {
    TextView textViewTitle;
    TextView textViewContent;
    public TabItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewContent = itemView.findViewById(R.id.textViewContent);
    }
}
