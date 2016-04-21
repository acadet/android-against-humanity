package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.Sort;

/**
 * ConversationDAO
 * <p>
 */
class ConversationDAO extends BaseDAO implements IConversationDAO {
    private IConversationMapper conversationMapper;

    ConversationDAO(RealmConfiguration realmConfiguration, IConversationMapper conversationMapper) {
        super(realmConfiguration);
        this.conversationMapper = conversationMapper;
    }

    @Override
    public List<Conversation> sortByDateDesc() {
        Realm dal = getRealmInstance();
        Map<String, Conversation> conversations = new HashMap<>();
        List<Conversation> outcome = new ArrayList<>();

        Stream
            .of(conversationMapper.map(dal.allObjects(ConversationDTO.class)))
            .forEach((e) -> conversations.put(e.getId(), e));

        Stream
            .of(dal.allObjectsSorted(
                MessageDTO.class,
                new String[] { "conversationId", "postedAt" },
                new Sort[] { Sort.ASCENDING, Sort.DESCENDING }
            ))
            .forEach((m) -> {
                if (conversations.containsKey(m.getConversationId())) {
                    outcome.add(conversations.get(m.getConversationId()));
                    conversations.remove(m.getConversationId());
                }
            });

        dal.close();

        return outcome;
    }

    @Override
    public Conversation save(String slug) {
        ConversationDTO conversation = new ConversationDTO();
        Realm dal = getRealmInstance();
        Conversation outcome;

        conversation.setId(UUID.randomUUID().toString());
        conversation.setSlug(slug);

        dal.beginTransaction();
        dal.copyToRealm(conversation);
        dal.commitTransaction();

        outcome = conversationMapper.map(conversation);
        dal.close();

        return outcome;
    }
}
