package com.expensesplit.repository;

import com.expensesplit.model.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
}
