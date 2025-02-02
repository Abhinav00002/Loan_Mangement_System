package com.lms.config;

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

import com.lms.service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	
	@Autowired
	private JwtUtils jwtUtil;
	
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 
		
		final String requestTokenHeader=request.getHeader("Authorization");
//		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken=null;
		
		
		
		if (requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			//yes
			
			jwtToken=requestTokenHeader.substring(7);
			
			try {
			username =this.jwtUtil.extractUsername(jwtToken);
 			
			}catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("jwt token has expired");
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println("error");

				}
			
		}else {
			
			
//			System.out.println("INVALID TOKEN, not start with bearer string");
		}
		//validated token
		if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			final UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
			if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
				
				
				//token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthentication =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication) ;
			}
			}else {
//				System.out.println("Token is not Valid");
			}
			filterChain.doFilter(request, response);
		
	}

	
	
	
	
	
	
	
	
//	  // Inject the token blacklist here
//    @Autowired
//    private TokenBlacklist tokenBlacklist;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        final String requestTokenHeader = request.getHeader("Authorization");
//        String username = null;
//        String jwtToken = null;
//
//        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//            jwtToken = requestTokenHeader.substring(7);
//
//            try {
//                username = this.jwtUtil.extractUsername(jwtToken);
//
//            } catch (ExpiredJwtException e) {
//                e.printStackTrace();
//                System.out.println("jwt token has expired");
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("error");
//            }
//
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            // Check if the token is blacklisted
//            if (!tokenBlacklist.isTokenBlacklisted(jwtToken)) {
//                final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//                if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthentication =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    usernamePasswordAuthentication.setDetails(
//                            new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
//                }
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }

}
