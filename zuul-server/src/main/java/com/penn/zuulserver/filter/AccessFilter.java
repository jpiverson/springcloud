package com.penn.zuulserver.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * zuul过滤器使用详解
 * 
 * @author penn
 *
 */
public class AccessFilter extends ZuulFilter {

	private static Logger logger = Logger.getLogger(AccessFilter.class);

	@Override
	public Object run() {
		// 过滤器的具体逻辑
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info("send {" + request.getMethod() + "} request {" + request.getRequestURL().toString() + "}");
		Object accessToken = request.getParameter("access_token");
		if (accessToken == null) {
			logger.warn("access token is empty");
			ctx.setSendZuulResponse(false);// 设置为false，表示不对该请求进行路由
			ctx.setResponseBody("sorry, the request has no access token");// 设置返回的内容
			ctx.setResponseStatusCode(401);
			return null;
		}
		logger.info("access token ok");
		return null;

	}

	@Override
	public boolean shouldFilter() {
		// 判断该过滤器是否需要被执行。
		return true;
	}

	@Override
	public int filterOrder() {
		/*
		 * 过滤器执行的顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。数值越小优先级越高
		 */
		return 0;
	}

	@Override
	public String filterType() {
		/*
		 * 过滤器的类型，决定过滤器在请求的哪个生命周中执行。pre 在请求路由之前执行。 routing 在路由请求时被调用。 post
		 * 在routing和error过滤器之后被调用。 error 处理请求时发生错误时被调用。
		 */
		return "pre";
	}

}
