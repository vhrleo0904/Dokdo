package kr.hs.dgsw.dokdo;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends PagerAdapter {

    private int[] images;
    private LayoutInflater inflater;
    private Context context;
    private ImageView imageView;
    //private TextView textView;

    public ImageAdapter(Context context, int id){
        this.context = context;

        if(id == 1) {
            images = new int[] {R.drawable.dokdo_intro1, R.drawable.dokdo_intro2};
        } else if(id == 2) {
            images = new int[] {R.drawable.dokdo_way1, R.drawable.dokdo_way2, R.drawable.dokdo_way3};
        } else if(id == 3) {
            images = new int[] {R.drawable.dokdo_nature1, R.drawable.dokdo_nature2, R.drawable.dokdo_nature3,
                                    R.drawable.dokdo_nature4, R.drawable.dokdo_nature5};
        } else if(id == 4) {
            images = new int[] {R.drawable.dokdo_history1, R.drawable.dokdo_history2, R.drawable.dokdo_history3};
        }
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ConstraintLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider, container, false);
        imageView = v.findViewById(R.id.imageView);
        //textView = v.findViewById(R.id.textView);

        imageView.setImageResource(images[position]);

        //textView.setVisibility(View.INVISIBLE);
        //String text = (position + 1) + "번째 이미지";
        //textView.setText(text);
        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
