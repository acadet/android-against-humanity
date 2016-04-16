package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Message;

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
