package com.adriencadet.droidagainsthumanity.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adriencadet.droidagainsthumanity.AndroidAgainstHumanityApplication;
import com.adriencadet.droidagainsthumanity.R;
import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.ui.helpers.DateFormatterHelper;
import com.adriencadet.droidagainsthumanity.ui.routers.IRouter;
import com.adriencadet.droidagainsthumanity.ui.screens.JoinConversationScreen;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ConversationListAdapter
 * <p>
 */
public class ConversationListAdapter extends BaseAdapter<Conversation> {

    @Inject
    @Named("main")
    IRouter mainRouter;

    class ViewHolder {
        @Bind(R.id.adapter_conversation_list_slug)
        TextView slug;

        @Bind(R.id.adapter_conversation_list_updated_at)
        TextView updatedAt;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public ConversationListAdapter(Context context, List<Conversation> items) {
        super(context, items);

        AndroidAgainstHumanityApplication.getApplicationComponent().inject(this);
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

        holder.slug.setText(item.getSlug());
        if (item.getUpdatedAt().isPresent()) {
            holder.updatedAt.setText(DateFormatterHelper.timeAgo(item.getUpdatedAt().get()));
        }

        view.setOnClickListener((v) -> {
            mainRouter.goTo(new JoinConversationScreen(item));
        });

        return view;
    }
}
