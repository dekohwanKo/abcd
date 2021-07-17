package com.care.root.mybatis.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {
	public List<BoardDTO> selectAllBoardList(@Param("s") int start,@Param("e") int end); //두개를 동시에 사용할시 @Param 사용
	public int writeSave(BoardDTO dto);
	public BoardDTO contentView(int writeNo);
	public void upHit(int writeNo);
	public int modify(BoardDTO dto);
	
	public int delete(int writeNo);
	
	public void addReply(BoardRepDTO dto);
	
	public List<BoardRepDTO>getRepList(int write_group);
	
	public int selectBoardCount();

}










