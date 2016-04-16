package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Conversation;

import java.util.List;

/**
 * IConversationMapper
 * <p>
 */
interface IConversationMapper {
    Conversation map(ConversationDTO source);

    List<Conversation> map(List<ConversationDTO> source);

    ConversationDTO map(Conversation source);
}
