package com.tensquare.friend.controller;

import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 17:20 2019/5/5
 */
@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @PutMapping("/like/{friendid}/{type}")
    public Result  addFriend(@PathVariable String friendid,@PathVariable String type){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"请先登录");
        }
        if(type.equals("1")){

            if(friendService.addFriend(claims.getId(), friendid)==0){
                return new Result(false,StatusCode.ACCESSERROR,"不要重复添加");
            }
        }else{
            friendService.addnoFriend(claims.getId(), friendid);
        }
        return new Result(true,StatusCode.OK,"操作成功");

    }

    @DeleteMapping("/{friendid}")
    public Result deleteFriend(@PathVariable String friendid){
        Claims claims=(Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }
        friendService.deleteFriend(claims.getId(), friendid);
        return new Result(true, StatusCode.OK, "删除成功");
    }



}
