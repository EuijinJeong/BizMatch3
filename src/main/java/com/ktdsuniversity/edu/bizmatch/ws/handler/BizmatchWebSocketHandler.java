package com.ktdsuniversity.edu.bizmatch.ws.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ktdsuniversity.edu.bizmatch.board.dao.BoardDao;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardVO;
import com.ktdsuniversity.edu.bizmatch.project.dao.ProjectDao;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;

@Component
public class BizmatchWebSocketHandler extends TextWebSocketHandler{
	public static final Logger logger = LoggerFactory.getLogger(BizmatchWebSocketHandler.class);
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private BoardDao boardDao;
	
	
	/**
	 * 웹 소켓에 연결되어있는 사용자(WebSocketSession)를 관리하는 변수
	 * Key : 접속된 사용자의 이메일(PK)
	 * Value : 웹 소켓 토큰 정보.
	 */
	private Map<String, WebSocketSession> connectedSessionMap;
	
	private Map<String, Map<String, String>> unconnectedSessionMap;
	
	private Gson gson;
	
	public BizmatchWebSocketHandler() {
		this.unconnectedSessionMap = new HashMap<>();
		this.connectedSessionMap = new HashMap<>();
		this.gson = new Gson();
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//message.getPayload() ==> 사용자가 보낸 텍스트 메시지를 꺼낸다.
				String payload = message.getPayload();
				
				// payload 에서 action 을 추출
				// payload 에서 message 을 추출
				// --> payload 를 Map 으로 변환.
				//    --> Gson Library 필요
				Map<String, String> payloadMap = new HashMap<>();
				try {
					payloadMap = gson.fromJson(payload, Map.class);
				}
				catch(JsonSyntaxException e) {
					System.err.println("잘못된 JSON 형식: " + e.getMessage());
				}
				
				String action = payloadMap.get("action");
				String receiveMessage = payloadMap.get("message");
				String nextUrl = payloadMap.get("url");
				
				
				if(action.equals("LOGIN")) {
					String email = payloadMap.get("email");
					if(unconnectedSessionMap.containsKey(email)) {
						sendToOneSession(unconnectedSessionMap.get(email), email);
						unconnectedSessionMap.remove(email);
					}
					//세션에 접속 함
					this.connectedSessionMap.put(email, session);
				}
				// 패널티 먹었을 때 알림
				else if(action.equals("RECEIVE_PENATLY")) {
					Map<String, String> textMessageMap = new HashMap<>();
					String receivePenatlyEmail = payloadMap.get("receivePenatlyEmail");
					
					textMessageMap.put("action", "RECEIVE_PENATLY");
					textMessageMap.put("message", receiveMessage);
					textMessageMap.put("receivePenatlyEmail", receivePenatlyEmail);
					
					sendToOneSession(textMessageMap, receivePenatlyEmail);
					
				}
				// 프로젝트에 새로운 댓글 작성되었을 때
				else if(action.equals("NEW_PJREPLY")) {
					Map<String, String> textMessageMap = new HashMap<>();
					String pjId = payloadMap.get("pjId");
					
					ProjectVO projectVO = this.projectDao.selectProjectInfo(pjId);
					String projectOrdrEmail = projectVO.getOrdrId();
					
					textMessageMap.put("action", "RECEIVE_PENATLY");
					textMessageMap.put("message", receiveMessage);
					textMessageMap.put("url", nextUrl);
					textMessageMap.put("projectOrdrEmail", projectOrdrEmail);
					
					sendToOneSession(textMessageMap, projectOrdrEmail);
					
				}
				// 내가 올린 문의글에 댓글이 작성되었을 경우
				else if(action.equals("NEW_BDREPLY")) {
					Map<String, String> textMessageMap = new HashMap<>();
					String boardId = payloadMap.get("pstId");
					BoardVO boardVO = this.boardDao.selectOneBoard(boardId);
					
					textMessageMap.put("action", "NEW_BDREPLY");
					textMessageMap.put("message", receiveMessage);
					textMessageMap.put("url", nextUrl);
					textMessageMap.put("boardWriter", boardVO.getAthrId());
					
					sendToOneSession(textMessageMap, boardVO.getAthrId());
				}
				// 인원모집 마감일이 되었을 경우
				else if(action.equals("DEADLINE_REQ")) {
					Map<String, String> textMessageMap = new HashMap<>();
					String pjId = payloadMap.get("pjId");
					ProjectVO projectVO = this.projectDao.selectProjectInfo(pjId);
					String pjRcutEndDt = projectVO.getPjRcrutEndDt();
					String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					
					if(pjRcutEndDt.equals(currentDate)) {
						textMessageMap.put("action", "DEADLINE_REQ");
						textMessageMap.put("projectOrderEmail", projectVO.getOrdrId());
						textMessageMap.put("url", nextUrl);
						textMessageMap.put("message", receiveMessage);
						sendToOneSession(textMessageMap, projectVO.getOrdrId());
					}
					else {
						return;
					}
				}
				// 결제 완료 요청 알람
				else if(action.equals("PAYMENT_REQ")) {
					Map<String, String> textMessageMap = new HashMap<>();
					String pjId = payloadMap.get("pjId");
					ProjectVO projectVO = this.projectDao.selectProjectInfo(pjId);
					String pjEndDt = projectVO.getEndDt();
					String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					
					if(pjEndDt.equals(currentDate)) {
						textMessageMap.put("action", "PAYMENT_REQ");
						textMessageMap.put("projectOrderEmail", projectVO.getOrdrId());
						textMessageMap.put("url", nextUrl);
						textMessageMap.put("message", receiveMessage);
						sendToOneSession(textMessageMap, projectVO.getOrdrId());
					}
					else {
						return;
					}
				}
	}
	
	
	
	/**
	 * 
	 * @param textMessageMap
	 * @param session
	 */
	private void sendToOneSession(Map<String, String> textMessageMap, String receiverEmail) {
		if(!this.connectedSessionMap.containsKey(receiverEmail)) {
			// 세션에 접속한 사람이 없을 경우
			this.unconnectedSessionMap.put(receiverEmail, textMessageMap);
		}
		else {
			TextMessage textMessage = new TextMessage(this.gson.toJson(textMessageMap));
			WebSocketSession session = this.connectedSessionMap.get(receiverEmail);
			if(session.isOpen()) {
				try {
					synchronized (session) {
						session.sendMessage(textMessage);
					}
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}	
		}
	}
	
}
