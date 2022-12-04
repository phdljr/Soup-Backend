package kr.ac.soup.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc // 웹 기능 활성화 <mvc:annotation-driven/>
@ComponentScan("kr.ac.soup")
public class WebConfig implements WebMvcConfigurer {

    // 톰캣이 기본으로 제공하는 서블릿을 사용하도록 설정
    // 요청에 관련된 빈이 없으면 톰캣에서 제공되는 디폴트 서블릿을 사용해서 처리함 -> 정적 파일을 읽는 용도로 많이 사용
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 나중에 ViewResolver 설정해두기(필요하면)
}
