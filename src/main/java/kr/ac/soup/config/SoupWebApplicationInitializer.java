package kr.ac.soup.config;


import kr.ac.soup.config.root.PersistenceConfig;
import kr.ac.soup.config.root.SwaggerConfig;
import kr.ac.soup.config.web.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

// 스프링 설정 파일을 읽어들이는 클래스
public class SoupWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // DispatcherServlet Mapping 설정
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // 비즈니스 로직 설정
    // ContextLoaderListner에 매핑되는 빈 설정(전역 설정 파일이고, DispatcherServlet보다 먼저 빈이 등록됨)
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{PersistenceConfig.class, SwaggerConfig.class};
    }

    // 스프링 MVC 설정 클래스 설정
    // DispatcherServlet에 매핑되는 빈 설정
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // 필터 설정
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[]{encodingFilter};
    }
}
