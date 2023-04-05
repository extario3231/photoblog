package hkmu.comps380f.photoblog;

import hkmu.comps380f.photoblog.model.dto.UserDto;
import hkmu.comps380f.photoblog.service.UserService;
import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

import static hkmu.comps380f.photoblog.model.UserRole.ADMIN;
import static hkmu.comps380f.photoblog.model.UserRole.USER;

@SpringBootApplication
public class PhotoblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoblogApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            userService.saveNewUser(new UserDto("chris", "chris", "hi", "75639562", "chris@hkmu.edu", List.of(USER)));
            userService.saveNewUser(new UserDto("sarah", "sarah", "hi", "65479853", "sarah@hkmu.edu", List.of(USER)));
            userService.saveNewUser(new UserDto("max", "max", "hi", "95213001", "max@hkmu.edu", List.of(USER, ADMIN)));
        };
    }

    @Bean
    public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                super.postProcessContext(context);
                JspPropertyGroup jspPropertyGroup = new JspPropertyGroup();
                jspPropertyGroup.addUrlPattern("*.jsp");
                jspPropertyGroup.addUrlPattern("*.jspf");
                jspPropertyGroup.setPageEncoding("UTF-8");
                jspPropertyGroup.setScriptingInvalid("true");
                jspPropertyGroup.addIncludePrelude("/WEB-INF/jsp/base.jspf");
                jspPropertyGroup.setTrimWhitespace("true");
                jspPropertyGroup.setDefaultContentType("text/html");
                JspPropertyGroupDescriptorImpl jspPropertyGroupDescriptor
                        = new JspPropertyGroupDescriptorImpl(jspPropertyGroup);
                context.setJspConfigDescriptor(
                        new JspConfigDescriptorImpl(
                                Collections.singletonList(jspPropertyGroupDescriptor),
                                Collections.emptyList()));
            }
        };
    }

}
