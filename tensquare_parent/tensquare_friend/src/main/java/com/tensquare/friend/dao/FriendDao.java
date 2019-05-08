package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 17:03 2019/5/5
 */
public interface FriendDao extends JpaRepository<Friend,String> {


    @Query("select count(f) from Friend f where  f.userid=?1 and  f.friendid=?2")
    public int selectCount(String userid,String friendid);


    @Modifying
    @Query("update  Friend  f SET  f.islike=?3 where  f.friendid=?2 and f.userid=?1")
    public void updateLike(String userid,String friendid,String islike);

    @Modifying
    @Query("delete  from Friend  f where f.userid=?1 and f.friendid=?2")
    public void deleteFriend(String userid,String friendid);



}
