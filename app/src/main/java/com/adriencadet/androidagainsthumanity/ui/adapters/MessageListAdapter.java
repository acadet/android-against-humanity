package com.adriencadet.androidagainsthumanity.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.beans.Message;
import com.adriencadet.androidagainsthumanity.ui.helpers.DateFormatterHelper;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MessageListAdapter
 * <p>
 */
public class MessageListAdapter extends BaseAdapter<Message> {

    class ViewHolder {
        @Bind(R.id.adapter_message_list_content)
        TextView content;

        @Bind(R.id.adapter_message_list_poster)
        TextView poster;

        @Bind(R.id.adapter_message_list_posted_at)
        TextView postedAt;

        @Bind({ R.id.adapter_message_list_content })
        List<TextView> labelsToColorize;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public MessageListAdapter(Context context) {
        super(context, new ArrayList<>());
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder holder;
        Message item = itemAt(i);
        int textColorId;

        view = recycle(R.layout.adapter_message_list, convertView, viewGroup);

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.content.setText(item.getContent());
        holder.postedAt.setText(DateFormatterHelper.timeAgo(item.getPostedAt()));

        if (item.isMine()) {
            holder.poster.setText(getContext().getString(R.string.from_me));
            textColorId = getContext().getResources().getColor(R.color.colorAccent);
        } else {
            holder.poster.setText(getContext().getString(R.string.from_who, item.getPosterNickname()));
            textColorId = getContext().getResources().getColor(R.color.colorPrimary);
        }

        Stream.of(holder.labelsToColorize).forEach((e) -> e.setTextColor(textColorId));

        return view;
    }
}
