package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * MessageDAO
 * <p>
 */
class MessageDAO extends BaseDAO implements IMessageDAO {

    private IMessageMapper messageMapper;
    private IUserDAO       userDAO;

    MessageDAO(RealmConfiguration realmConfiguration) {
        super(realmConfiguration);
    }

    @Override
    public List<Message> sortByDateAsc(Conversation conversation) {
        return messageMapper
            .map(
                getRealmInstance()
                    .where(MessageDTO.class)
                    .equalTo("conversationId", conversation.getId())
                    .findAllSorted("postedAt")
            );
    }

    @Override
    public Message saveOutgoing(Conversation conversation, String content) {
        MessageDTO message = new MessageDTO();
        Realm dal = getRealmInstance();

        message.setId(UUID.randomUUID().toString());
        message.setMine(true);
        message.setContent(content);
        message.setPosterNickname(userDAO.getNickname());
        message.setConversationId(conversation.getId());
        message.setPostedAt(DateTime.now().toDate());

        dal.beginTransaction();
        dal.copyToRealm(message);
        dal.commitTransaction();

        return messageMapper.map(message);
    }

    @Override
    public void save(Conversation conversation, Message message) {
        Realm dal = getRealmInstance();
        MessageDTO messageDTO = messageMapper.map(message);

        messageDTO.setConversationId(conversation.getId());
        dal.beginTransaction();
        dal.copyToRealm(messageDTO);
        dal.commitTransaction();
    }
}
