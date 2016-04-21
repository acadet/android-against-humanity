package com.adriencadet.androidagainsthumanity.ui.adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.beans.Message;

import org.joda.time.DateTime;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MessageListAdapter
 * <p>
 */
public class MessageListAdapter extends BaseAdapter<Message> {

    public class ViewHolder {
        @Bind(R.id.adapter_message_list_content)
        TextView content;

        @Bind(R.id.adapter_message_list_poster)
        TextView poster;

        @Bind(R.id.adapter_message_list_posted_at)
        TextView postedAt;

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

        view = recycle(R.layout.adapter_message_list, convertView, viewGroup);

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.content.setText(item.getContent());
        holder.poster.setText(getContext().getString(R.string.from_who, item.getPosterNickname()));
        holder.postedAt.setText(
            DateUtils
                .getRelativeTimeSpanString(
                    item.getPostedAt().toDate().getTime(),
                    DateTime.now().toDate().getTime(),
                    DateUtils.SECOND_IN_MILLIS
                )
        );

        return view;
    }
}
