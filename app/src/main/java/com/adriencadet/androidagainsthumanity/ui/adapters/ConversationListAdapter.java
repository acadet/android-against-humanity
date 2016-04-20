package com.adriencadet.androidagainsthumanity.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.beans.Conversation;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ConversationListAdapter
 * <p>
 */
public class ConversationListAdapter extends BaseAdapter<Conversation> {

    private static class ViewHolder {
        @Bind(R.id.adapter_conversation_list_slug)
        TextView slugView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public ConversationListAdapter(Context context, List<Conversation> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        Conversation item = itemAt(position);

        view = recycle(R.layout.adapter_conversation_list, convertView, parent);

        if (convertView != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.slugView.setText(item.getSlug());

        return view;
    }
}
