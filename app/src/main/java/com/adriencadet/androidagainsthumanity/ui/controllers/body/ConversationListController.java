package com.adriencadet.androidagainsthumanity.ui.controllers.body;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.ui.adapters.ConversationListAdapter;
import com.adriencadet.androidagainsthumanity.ui.controllers.BaseController;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import butterknife.Bind;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * ConversationListController
 * <p>
 */
public class ConversationListController extends BaseController {
    private Subscription sortByDateDescSubscription;

    @Bind(R.id.conversation_list_listview)
    ListView listView;

    @Bind(R.id.conversation_list_no_content_label)
    TextView noContentLabelView;

    private void showView(View view) {
        YoYo.with(Techniques.FadeIn).delay(300).playOn(view);
    }

    @Override
    protected int layoutId() {
        return R.layout.conversation_list;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        sortByDateDescSubscription = conversationBLL
            .sortByDateDesc()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<List<Conversation>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);

                    listView.setVisibility(View.GONE);
                    showView(noContentLabelView);
                }

                @Override
                public void onNext(List<Conversation> conversations) {
                    if (conversations.isEmpty()) {
                        noContentLabelView.setVisibility(View.GONE);
                        listView.setAdapter(new ConversationListAdapter(context, conversations));
                        showView(listView);
                    } else {
                        listView.setVisibility(View.GONE);
                        showView(noContentLabelView);
                    }
                }
            });
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (sortByDateDescSubscription != null) {
            sortByDateDescSubscription.unsubscribe();
        }
    }
}
