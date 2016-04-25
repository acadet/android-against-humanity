package com.adriencadet.droidagainsthumanity.dao;

import com.adriencadet.droidagainsthumanity.beans.Conversation;

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
