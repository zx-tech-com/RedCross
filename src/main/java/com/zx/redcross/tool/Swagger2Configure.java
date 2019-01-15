package com.zx.redcross.tool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.classmate.TypeResolver;
import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeComent;
import com.zx.redcross.entity.TopicComent;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.zx.redcross"})
public class Swagger2Configure {

	@Bean
	public Docket createRestApi() {
		
		TypeResolver typeResolver = new TypeResolver();
		AlternateTypeRule typeResult1 = new AlternateTypeRule(typeResolver.resolve(Course.class),typeResolver.resolve(Object.class));
		AlternateTypeRule typeResult2 = new AlternateTypeRule(typeResolver.resolve(Knowledge.class),typeResolver.resolve(Object.class));
		AlternateTypeRule typeResult3 = new AlternateTypeRule(typeResolver.resolve(KnowledgeComent.class),typeResolver.resolve(Object.class));
		AlternateTypeRule typeResult4 = new AlternateTypeRule(typeResolver.resolve(TopicComent.class),typeResolver.resolve(Object.class));
		AlternateTypeRule typeResult5 = new AlternateTypeRule(typeResolver.resolve(CourseSubject.class),typeResolver.resolve(Object.class));
//		AlternateTypeRule typeResult6 = new AlternateTypeRule(typeResolver.resolve(ExamOrder.class),typeResolver.resolve(Object.class));
		
		return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .alternateTypeRules(typeResult1,typeResult2,typeResult3,typeResult4,typeResult5/*,typeResult6*/)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(FrontEnd.class))
                .paths(PathSelectors.any())
                .build();
	}	
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("红十字API文档")
                .description("测试版本")
                .version("0.1")
                .build();
    }

	
}
