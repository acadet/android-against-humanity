package com.adriencadet.androidagainsthumanity.ui.controllers.floating;

import android.widget.TextView;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationExtendedScreen;
import com.lyft.scoop.Screen;

import org.javatuples.Pair;

import java.util.List;

import butterknife.Bind;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * ConversationExtendedController
 * <p>
 */
public class ConversationExtendedController extends BaseController {
    private Subscription listSuggestionSubscription;

    @Bind({ R.id.conversation_actions_suggestion_1,
            R.id.conversation_actions_suggestion_2,
            R.id.conversation_actions_suggestion_3,
            R.id.conversation_actions_suggestion_4 })
    List<TextView> textViewList;

    @Override
    protected int layoutId() {
        return R.layout.conversation_actions;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        ConversationExtendedScreen screen = Screen.fromController(this);

        listSuggestionSubscription = messageBLL
            .listSuggestions()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<Pair<List<String>, List<String>>>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onNext(Pair<List<String>, List<String>> suggestions) {
                    int i = 0;
                    for (String prefix : suggestions.getValue0()) {
                        TextView t1 = textViewList.get(i++);
                        t1.setText(prefix);
                        t1.setOnClickListener((v1) -> {
                            int j = 0;
                            for (String suffix : suggestions.getValue1()) {
                                TextView t2 = textViewList.get(j++);
                                t2.setText(suffix);
                                t2.setOnClickListener((v2) -> {
                                    if (screen.hasConversation()) {
                                        messageBLL.post(screen.conversation, prefix, suffix);
                                    } else {
                                        messageBLL.post(screen.slug, prefix, suffix);
                                    }
                                });
                            }
                        });
                    }
                }
            });
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (listSuggestionSubscription != null) {
            listSuggestionSubscription.unsubscribe();
        }
    }
}
