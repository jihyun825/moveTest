package kr.or.ddit.mapper;

import kr.or.ddit.vo.DDITMemberVO;

public interface LoginMapper {

	public DDITMemberVO idCheck(String memId);

	public int signup(DDITMemberVO memberVo);

	public DDITMemberVO loginCheck(DDITMemberVO memberVo);

	public String idForgetProcess(DDITMemberVO member);

	public String pwForgetProcess(DDITMemberVO member);

}
