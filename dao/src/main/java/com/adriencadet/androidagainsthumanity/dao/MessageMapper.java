package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Message;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.joda.time.DateTime;

import java.util.List;

/**
 * MessageMapper
 * <p>
 */
class MessageMapper implements IMessageMapper {
    @Override
    public Message map(MessageDTO source) {
        return new Message()
            .setId(source.getId())
            .setMine(source.isMine())
            .setPostedAt(new DateTime(source.getPostedAt()))
            .setContent(source.getContent())
            .setPosterNickname(source.getPosterNickname());
    }

    @Override
    public List<Message> map(List<MessageDTO> source) {
        return Stream.of(source).map(this::map).collect(Collectors.toList());
    }

    @Override
    public MessageDTO map(Message source) {
        MessageDTO outcome = new MessageDTO();

        outcome.setId(source.getId());
        outcome.setPostedAt(source.getPostedAt().toDate());
        outcome.setPosterNickname(source.getPosterNickname());
        outcome.setContent(source.getContent());
        outcome.setMine(source.isMine());

        return outcome;
    }
}
