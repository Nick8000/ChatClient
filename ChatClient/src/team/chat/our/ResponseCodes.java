package team.chat.our;

import java.io.Serializable;

public enum ResponseCodes implements Serializable
{
	UserAdded,	
	UserSignedIn,
	UserDeleted,
	UserLogOut,
	UserExists,
	UserUpdated,
	UserNotFound,
	
	addToChatRoom,
	MessageIsDelivered,
	sendMessageToChatRoom,
	newRoom,
	newMsgInRoom,
	BadRequest,
	newUserInRoom
}
