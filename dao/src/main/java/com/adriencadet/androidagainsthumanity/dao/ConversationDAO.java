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

    private ConversationDTO findBySlug(Realm dal, String slug) {
        return dal.where(ConversationDTO.class).equalTo("slug", slug).findFirst();
    }

    @Override
    public List<Conversation> sortByDateDesc() {
        Realm dal = getRealmInstance();
        Map<String, ConversationDTO> conversations = new HashMap<>();
        List<Conversation> outcome = new ArrayList<>();

        Stream
            .of(dal.allObjects(ConversationDTO.class))
            .forEach((e) -> conversations.put(e.getId(), e));

        Stream
            .of(dal.allObjectsSorted(
                MessageDTO.class,
                new String[] { "conversationId", "postedAt" },
                new Sort[] { Sort.ASCENDING, Sort.DESCENDING }
            ))
            .forEach((m) -> {
                if (conversations.containsKey(m.getConversationId())) {
                    outcome.add(conversationMapper.map(conversations.get(m.getConversationId())));
                    conversations.remove(m.getConversationId());
                }
            });

        dal.beginTransaction();
        Stream.of(conversations).forEach((entry) -> entry.getValue().removeFromRealm());
        dal.commitTransaction();

        dal.close();

        return outcome;
    }

    @Override
    public Conversation findBySlug(String slug) {
        Realm dal = getRealmInstance();
        Conversation outcome;
        ConversationDTO source;

        source = findBySlug(dal, slug);
        if (source == null) {
            dal.close();
            return null;
        }

        outcome = conversationMapper.map(source);
        dal.close();

        return outcome;
    }

    @Override
    public Conversation save(String slug) {
        ConversationDTO conversation;
        Realm dal = getRealmInstance();
        Conversation outcome;

        conversation = findBySlug(dal, slug);
        if (conversation == null) {
            conversation = new ConversationDTO();
            conversation.setId(UUID.randomUUID().toString());
            conversation.setSlug(slug);

            dal.beginTransaction();
            dal.copyToRealm(conversation);
            dal.commitTransaction();
        }

        outcome = conversationMapper.map(conversation);
        dal.close();

        return outcome;
    }
}
