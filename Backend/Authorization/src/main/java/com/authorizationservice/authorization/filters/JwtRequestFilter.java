package com.authorizationservice.authorization.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authorizationservice.authorization.service.AuthUserDetailService;
import com.authorizationservice.authorization.util.JwtUtil.JwtUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class JwtRequestFilter  extends OncePerRequestFilter{
	
	@Autowired
	private AuthUserDetailService authUserDetailService;

	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain chain) throws ServletException,IOException {
		final String authorizationHeader=request.getHeader("Authorization");
		String username=null;
		String jwt=null;
		
		try {
			if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")) {
				jwt=authorizationHeader.substring(7);
				username=jwtUtil.extractUsername(jwt);
				
			}
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails = this.authUserDetailService.loadUserByUsername(username);
				if(Boolean.TRUE.equals(jwtUtil.validateToken(jwt, userDetails))) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
							userDetails,null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
				}
			}
			
			
			
		}
		catch (Exception e) {

			logger.error("[EXCEPTION IN Request Filter]"+ e.toString());
		}
		finally {
			chain.doFilter(request, response);
		}
	}
}
