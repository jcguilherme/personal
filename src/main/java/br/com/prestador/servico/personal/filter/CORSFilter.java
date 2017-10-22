package br.com.prestador.servico.personal.filter;
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        	HttpServletResponse httpResponse = (HttpServletResponse) response;
	        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
	        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        	httpResponse.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type");
	        httpResponse.setHeader("Access-Control-Expose-Headers", "custom-header1, custom-header2");
        	httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
	        httpResponse.setHeader("Access-Control-Max-Age", "4800");
	        
	        
	        
	        WrappedRequest wrappedRequest = new WrappedRequest((HttpServletRequest)request);
	        System.out.println(wrappedRequest._body);
	        String url = ((HttpServletRequest) request).getPathTranslated();
	        System.out.println(url);
	        //target = ParseExtDirectTargetFrom(wrappedRequest);
	        request = wrappedRequest;
	        chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}
} 