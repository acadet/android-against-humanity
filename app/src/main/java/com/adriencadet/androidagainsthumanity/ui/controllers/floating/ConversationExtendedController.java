package com.adriencadet.androidagainsthumanity.ui.controllers.floating;

import android.widget.ListView;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.ui.controllers.BaseController;

import butterknife.Bind;

/**
 * ConversationExtendedController
 * <p>
 */
public class ConversationExtendedController extends BaseController {

    @Bind(R.id.conversation_actions_listview)
    ListView listView;

    @Override
    protected int layoutId() {
        return R.layout.conversation_actions;
    }
}
