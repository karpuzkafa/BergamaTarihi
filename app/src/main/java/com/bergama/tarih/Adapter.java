package com.bergama.tarih;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class Adapter extends PagerAdapter {
    private Context context;
    private ArrayList<PageModel> modelArrayList;

    public Adapter(Context context, ArrayList<PageModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false);

        ImageView resim = view.findViewById(R.id.resim_iv);
        TextView baslik = view.findViewById(R.id.baslik_tv);
        TextView aciklama = view.findViewById(R.id.aciklama_tv);

        PageModel model = modelArrayList.get(position);

        resim.setImageResource(model.getImage());
        baslik.setText(model.getTitle());
        aciklama.setText(model.getDescription());

        view.setOnClickListener(v -> {
            Toast.makeText(context, "Tıklandı", Toast.LENGTH_SHORT).show();
        });

        container.addView(view);
        return view;
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
