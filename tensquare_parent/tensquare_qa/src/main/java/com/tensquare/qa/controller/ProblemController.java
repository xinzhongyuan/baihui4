package com.tensquare.qa.controller;
import java.util.List;
import java.util.Map;

import com.tensquare.qa.client.LabelClient;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	
	 @Autowired
     private HttpServletRequest request;
	/**
	 * 查询全部数据
	 */

	@Autowired
	private LabelClient labelClient;

	@RequestMapping(value = "/lable/{labelid}")
	public Result  findByLabelId(@PathVariable  String labelid){
		System.out.println(123);
		Result  result = labelClient.findById(labelid);

		return result;
	}


	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }


	/**
	 * 增加
	 * @param problem
	 */

	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		Claims user_claims = (Claims) request.getAttribute("user_claims");
		if(user_claims ==null){
			return new Result(true,StatusCode.OK,"无权访问");
		}
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	@GetMapping("/newlist/{labelid}/{page}/{size}")
	public Result findNewListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pagelist = problemService.findByProblem(labelid, page, size);
		PageResult<Problem> pageresult = new PageResult(pagelist.getTotalElements(), pagelist.getContent());
		return new Result(true, StatusCode.OK, "查询成功", pageresult);
	}


	@GetMapping("/hotlist/{labelid}/{page}/{size}")
	public Result findhotListlabeid(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pagehot = problemService.findhotProblem(labelid, 1, 2);
		PageResult<Problem> pageresult = new PageResult<>(pagehot.getTotalElements(), pagehot.getContent());
		return new Result(true, StatusCode.OK, "查询成功", pageresult);
	}

	@GetMapping("/waitlist/{labelid}/{page}/{size}")
	public Result findwaitListlabeid(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pagehot = problemService.findwaitProblem(labelid, 1, 2);
		PageResult<Problem> pageresult = new PageResult<>(pagehot.getTotalElements(), pagehot.getContent());
		return new Result(true, StatusCode.OK, "查询成功", pageresult);
	}

}
