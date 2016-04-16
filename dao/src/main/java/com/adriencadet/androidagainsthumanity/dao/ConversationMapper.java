package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.List;

/**
 * ConversationMapper
 * <p>
 */
class ConversationMapper implements IConversationMapper {
    @Override
    public Conversation map(ConversationDTO source) {
        return new Conversation()
            .setId(source.getId())
            .setSlug(source.getSlug());
    }

    @Override
    public List<Conversation> map(List<ConversationDTO> source) {
        return Stream.of(source).map(this::map).collect(Collectors.toList());
    }

    @Override
    public ConversationDTO map(Conversation source) {
        ConversationDTO c = new ConversationDTO();
        c.setId(source.getId());
        c.setSlug(source.getSlug());
        return c;
    }
}
