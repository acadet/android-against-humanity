package com.adriencadet.androidagainsthumanity.ui.controllers.floating;

import android.widget.ListView;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.ui.adapters.MessageSuggestionAdapter;
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
    private Subscription                     listSuggestionSubscription;
    private Pair<List<String>, List<String>> suggestions;

    @Bind(R.id.conversation_actions_listview)
    ListView listView;

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
                    listView.setAdapter(
                        new MessageSuggestionAdapter(
                            context,
                            suggestions.getValue0(),
                            true,
                            (s1) -> {
                                listView.setAdapter(
                                    new MessageSuggestionAdapter(
                                        context,
                                        suggestions.getValue1(),
                                        false,
                                        (s2) -> {
                                            if (screen.hasConversation()) {
                                                messageBLL.post(screen.conversation, s1, s2);
                                            } else {
                                                messageBLL.post(screen.slug, s1, s2);
                                            }
                                        }
                                    )
                                );
                            }
                        )
                    );
                }

                @Override
                public void onNext(Pair<List<String>, List<String>> objects) {
                    suggestions = objects;
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
