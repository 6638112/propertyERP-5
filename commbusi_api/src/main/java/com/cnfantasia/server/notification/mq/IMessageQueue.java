package com.cnfantasia.server.notification.mq;

import java.util.List;

public interface IMessageQueue<E> {
	public void addMessage(E entity);
	public E pollMessage();
	
	public List<E> fetchAllMessage();
}
