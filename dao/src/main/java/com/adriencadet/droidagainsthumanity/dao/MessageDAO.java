package com.adriencadet.droidagainsthumanity.dao;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.beans.Message;

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

    MessageDAO(RealmConfiguration realmConfiguration, IMessageMapper messageMapper, IUserDAO userDAO) {
        super(realmConfiguration);
        this.messageMapper = messageMapper;
        this.userDAO = userDAO;
    }

    @Override
    public List<Message> sortByDateAsc(Conversation conversation) {
        Realm dal = getRealmInstance();
        List<Message> outcome;

        outcome = messageMapper
            .map(
                dal
                    .where(MessageDTO.class)
                    .equalTo("conversationId", conversation.getId())
                    .findAllSorted("postedAt")
            );
        dal.close();

        return outcome;
    }

    @Override
    public Message saveOutgoing(Conversation conversation, String content) {
        MessageDTO message = new MessageDTO();
        Message outcome;
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

        outcome = messageMapper.map(message);

        dal.close();

        return outcome;
    }

    @Override
    public void save(Conversation conversation, Message message) {
        Realm dal = getRealmInstance();
        MessageDTO messageDTO = messageMapper.map(message);

        messageDTO.setConversationId(conversation.getId());
        dal.beginTransaction();
        dal.copyToRealm(messageDTO);
        dal.commitTransaction();
        dal.close();
    }
}
