package com.expensesplit.repository;

import com.expensesplit.model.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
    List<GroupMember> findByGroup_GroupId(Integer groupId);
}
