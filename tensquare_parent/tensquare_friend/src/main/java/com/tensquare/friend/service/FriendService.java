package com.tensquare.friend.service;

import com.netflix.discovery.converters.Auto;
import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.hibernate.engine.profile.FetchProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 17:11 2019/5/5
 */
@Service
public class FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao nofriendDao;

    @Autowired
    private UserClient userClient;


    @Transactional
    public void addnoFriend(String userid, String friendid){
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        nofriendDao.save(noFriend);
        //friendDao.deleteFriend(userid, friendid);
        //friendDao.updateLike(friendid, userid, "0");
    }


    @Transactional
    public void deleteFriend(String userid,String friendid){
        friendDao.deleteFriend(userid,friendid);
        friendDao.updateLike(friendid,userid,"0");
        addnoFriend(userid,friendid);//向不喜欢表中添加记录
        userClient.incFollowcount(userid, -1);
        userClient.incFollowcount(friendid, -1);
    }

    @Transactional
    public int addFriend(String userid, String friendid) {
        int count = friendDao.selectCount(userid, friendid);
        if (count > 0) {
            return 0;
        }
        Friend friend = new Friend();
        friend.setFriendid(friendid);
        friend.setUserid(userid);
        friend.setIslike("0");
        friendDao.save(friend);
        userClient.incFollowcount(userid, 1);
        userClient.incFollowcount(friendid, 1);
        if (friendDao.selectCount(friendid, userid) > 0) {
            friendDao.updateLike(userid, friendid, "1");
            friendDao.updateLike(friendid, userid, "1");
        }

        return 1;

    }


}
