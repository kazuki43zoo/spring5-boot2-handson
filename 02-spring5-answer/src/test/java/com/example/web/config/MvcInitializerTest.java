package com.example.web.config;

import com.example.persistence.config.DataSourceConfig;
import com.example.persistence.config.JdbcConfig;
import com.example.service.config.ServiceConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import static org.junit.jupiter.api.Assertions.*;

public class MvcInitializerTest {

    MvcInitializer mvcInitializer = new MvcInitializer();

    @Test
    @DisplayName("AbstractAnnotationConfigDispatcherServletInitializerを継承している")
    public void classTest() {
        Object obj = mvcInitializer; // 未継承時のコンパイルエラー回避のため一旦Object型変数に代入
        assertTrue(obj instanceof AbstractAnnotationConfigDispatcherServletInitializer);
    }

    @Test
    @DisplayName("getRootConfigClasses()がnullを返す")
    public void getRootConfigClassesTest() {
        assertNull(mvcInitializer.getRootConfigClasses(), "must be null");
    }

    @Test
    @DisplayName("getServletConfigClasses()がJava Configの配列を返す")
    public void getServletConfigClassesTest() {
        Class[] expectedClasses = {DataSourceConfig.class, JdbcConfig.class, ServiceConfig.class, MvcConfig.class};
        Class<?>[] configClasses = mvcInitializer.getServletConfigClasses();
        assertArrayEquals(expectedClasses, configClasses, "config classes not correct");
    }

    @Test
    @DisplayName("getServletMappings()が/を返す")
    public void getServletMappingsTest() {
        String[] servletMappings = mvcInitializer.getServletMappings();
        assertArrayEquals(new String[]{"/"}, servletMappings, "servlet mapping not correct");
    }

    @Test
    @DisplayName("getServletFilters()はオーバーライドされていない")
    public void getServletFiltersTest() {
        assertThrows(NoSuchMethodException.class,
                () -> MvcInitializer.class.getDeclaredMethod("getServletFilters"),
                "must not be defined");
    }
}
