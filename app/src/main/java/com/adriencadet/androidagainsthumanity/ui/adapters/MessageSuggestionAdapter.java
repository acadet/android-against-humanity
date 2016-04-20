package com.adriencadet.androidagainsthumanity.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adriencadet.androidagainsthumanity.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MessageSuggestionAdapter
 * <p>
 */
public class MessageSuggestionAdapter extends BaseAdapter<String> {
    public interface IObserver {
        void onSelection(String value);
    }

    class ViewHolder {
        @Bind(R.id.adapter_message_suggestion_label)
        TextView label;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.adapter_message_suggestion_label)
        void onLabelClick() {
            observer.onSelection(label.getText().toString());
        }
    }

    private boolean   isPrimary;
    private IObserver observer;

    public MessageSuggestionAdapter(Context context, List<String> items, boolean isPrimary, IObserver observer) {
        super(context, items);
        this.isPrimary = isPrimary;
        this.observer = observer;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder viewHolder;
        String item = itemAt(i);

        view = recycle(R.layout.adapter_message_suggestion, convertView, viewGroup);

        if (convertView != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder.label.setText(item);

        return view;
    }
}
