package ai.blogapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ai.blogapp.interceptor.AdminInterceptor;
import ai.blogapp.interceptor.LoginInterceptor;
import ai.blogapp.interceptor.SuperadminInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private final LoginInterceptor login;
	private final AdminInterceptor admin;
	private final SuperadminInterceptor superadmin;
	
	public WebConfig(LoginInterceptor login, AdminInterceptor admin, SuperadminInterceptor superadmin) {
		this.login = login;
		this.admin = admin;
		this.superadmin = superadmin;
	}

    @Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(login).addPathPatterns("/**").excludePathPatterns("/login", "/signup", "/css/**", "/js/**", "/images/**", "/fonts/**", "/favicon.ico");
		
		registry.addInterceptor(admin).addPathPatterns("/tags/**", "/categories/**");
		
		registry.addInterceptor(superadmin).addPathPatterns("/users/**");
	}



	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Maps web requests like http://localhost:8080/images/filename.jpg 
        // to your local directory D:/JWD (Java Web Development)/Dev/sevletprojects/posts_img/
        
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:/JWD (Java Web Development)/Dev/sevletprojects/posts_img/");
    }
}