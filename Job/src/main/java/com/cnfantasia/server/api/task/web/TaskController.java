package com.cnfantasia.server.api.task.web;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName: TaskController
 * @Date: 2016-12-14 11:30
 * @Auther: kangduo
 * @Description: ()
 */
@RequestMapping(value = "task")
@Controller
public class TaskController extends BaseController {

    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView taskList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (!"localhost".equals(request.getServerName())
                && !"120.25.83.136".equals(request.getServerName())
                && !"123.57.19.77".equals(request.getServerName())) {
            mav.setViewName("/error/404");
        } else {
            mav.setViewName("/task/taskList");
        }
        return mav;
    }

    @RequestMapping(value="/executeTask.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse executeTask(HttpServletRequest request, String taskName, String methodName)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        JsonResponse jsonResponse = new JsonResponse();
        if(!"localhost".equals(request.getServerName())
                && !"120.25.83.136".equals(request.getServerName())
                && !"123.57.19.77".equals(request.getServerName())){
            jsonResponse.setMessage("接口不存在");
            jsonResponse.setStatus("0001");
            return jsonResponse;
        }

        Object task = CnfantasiaCommbusiUtil.getBeanManager(taskName);
        Method method = task.getClass().getDeclaredMethod(methodName, null);
        method.invoke(task, null);

        return jsonResponse;
    }
}
