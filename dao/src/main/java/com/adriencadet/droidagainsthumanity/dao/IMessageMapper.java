package com.adriencadet.droidagainsthumanity.dao;

import com.adriencadet.droidagainsthumanity.beans.Message;

import java.util.List;

/**
 * IMessageMapper
 * <p>
 */
interface IMessageMapper {
    Message map(MessageDTO source);

    List<Message> map(List<MessageDTO> source);

    MessageDTO map(Message source);
}
