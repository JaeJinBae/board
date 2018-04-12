package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.BoardVO;

public interface BoardDao {
	public List<BoardVO> selectAll();
	public BoardVO selectOne(int bno);
	public void insert(BoardVO vo);
	public void update(BoardVO vo);
	public void updateCnt(int bno);
	public void delete(int bno);
}
