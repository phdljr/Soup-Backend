package kr.ac.soup.config;

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
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    // 스프링 설정 클래스 설정
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
