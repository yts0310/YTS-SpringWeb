package com.yts.chap11;
import java.util.List;

public interface MemberDao {
	Member SelectByEmail(String email);
	void insert(Member member);
	void update(Member member);
	List<Member> selectAll(int offset, int count);
	int countAll();
}
