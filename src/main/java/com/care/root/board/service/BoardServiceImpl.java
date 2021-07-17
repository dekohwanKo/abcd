package com.care.root.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.member.session_name.MemberSessionName;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper mapper;
	public void selectAllBoardList(Model model, int num) {
		int pageLetter = 3; //한페이지에 몇 개의 글을 보여줄지
		int allCount = mapper.selectBoardCount(); //글 총 개수 얻어오기
		
		int repeat = allCount / pageLetter;
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		// num=1 * 3
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		model.addAttribute("repeat", repeat);
		
		model.addAttribute("boardList", mapper.selectAllBoardList(start, end));
	}
	public String writeSave(MultipartHttpServletRequest mul, 
			HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));
		HttpSession session = request.getSession();
		dto.setId( (String)session.getAttribute(MemberSessionName.LOGIN)); //로그인한 사용자만
		
		MultipartFile file = mul.getFile("image_file_name");
		BoardFileService bfs = new BoardFileServiceImpl();
		if(file.getSize()!=0) {
			dto.setImageFileName(bfs.saveFile(file));
		}else {
			dto.setImageFileName("nan");
			
		}
		return bfs.getMessage(mapper.writeSave(dto), request);  //데이터 베이스에 저장하겠다.
	}
	public void contentView(int writeNo, Model model) {
		model.addAttribute("personalData",mapper.contentView(writeNo));
		upHit(writeNo);
		
		
	}
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	public String modify(MultipartHttpServletRequest mul,
						HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setWriteNo(Integer.parseInt(mul.getParameter("writeNo")) );
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));
		MultipartFile file = mul.getFile("imageFileName");
		BoardFileService bfs = new BoardFileServiceImpl();
		if(file.getSize() != 0) {
			dto.setImageFileName(bfs.saveFile(file));
			bfs.deleteImage(mul.getParameter("originFileName"));
		}else {
			dto.setImageFileName(mul.getParameter("originFileName"));
		}
		int result = mapper.modify(dto);
		String msg, url;
		String path = request.getContextPath();
		if(result == 1 ) {
			msg = "성공적으로 수정되었습니다";
			url = path+"/board/boardAllList";
		}else {
			msg = "수정 중 문제 발생";
			url = path+ "/board/modify_form";
		}
		return bfs.getMessage(msg, url);
	}
	public String boardDelete(int writeNo, String imageFileName, 
			HttpServletRequest request) {
		
		String msg, url;
		String path = request.getContextPath();
		
		int result = mapper.delete(writeNo);
		BoardFileService bfs = new BoardFileServiceImpl();
		
		if(result == 1) {
			bfs.deleteImage(imageFileName);
			msg = "성공적으로 삭제 되었습니다.";
			url = path+ "/board/boardAllList";
		}else {
			msg = "삭제중 문제 발생!!!.";
			url = path+ "/board/contentView";
		}
		return bfs.getMessage(msg, url);
	}
	
	public void addReply(BoardRepDTO dto) {
		mapper.addReply(dto);
	}
	public List<BoardRepDTO>getRepList(int write_group){
		return mapper.getRepList(write_group);
	}

}








