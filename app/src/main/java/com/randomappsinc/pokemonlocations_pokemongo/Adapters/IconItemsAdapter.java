package com.randomappsinc.pokemonlocations_pokemongo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.randomappsinc.pokemonlocations_pokemongo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 7/15/16.
 */
public class IconItemsAdapter extends BaseAdapter {
    private Context context;
    private String[] itemNames;
    private String[] itemIcons;

    public IconItemsAdapter(Context context, int optionIds, int iconIds) {
        this.context = context;
        this.itemNames = context.getResources().getStringArray(optionIds);
        this.itemIcons = context.getResources().getStringArray(iconIds);
    }

    @Override
    public int getCount() {
        return itemNames.length;
    }

    @Override
    public String getItem(int position) {
        return itemNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class IconItemViewHolder {
        @Bind(R.id.item_icon) TextView itemIcon;
        @Bind(R.id.item_name) TextView itemName;

        public IconItemViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public View getView(int position, View view, ViewGroup parent) {
        IconItemViewHolder holder;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.icon_item_cell, parent, false);
            holder = new IconItemViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (IconItemViewHolder) view.getTag();
        }

        holder.itemName.setText(itemNames[position]);
        holder.itemIcon.setText(itemIcons[position]);

        return view;
    }
}
