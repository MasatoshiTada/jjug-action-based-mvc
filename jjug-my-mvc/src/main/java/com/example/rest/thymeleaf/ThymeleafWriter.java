package com.example.rest.thymeleaf;

import org.glassfish.jersey.internal.util.collection.Ref;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@Provider
@Produces(MediaType.TEXT_HTML)
public class ThymeleafWriter implements MessageBodyWriter<ThymeleafViewable> {

    private TemplateEngine templateEngine;

    @Inject
    private javax.inject.Provider<Ref<HttpServletRequest>> requestProviderRef;
    @Inject
    private javax.inject.Provider<Ref<HttpServletResponse>> responseProviderRef;
    @Inject
    private ServletContext servletContext;

    @PostConstruct
    public void init() {
        ServletContextTemplateResolver templateResolver =
                new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return ThymeleafViewable.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(ThymeleafViewable thymeleafViewable, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(ThymeleafViewable thymeleafViewable, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        HttpServletRequest httpServletRequest = requestProviderRef.get().get();
        HttpServletResponse httpServletResponse = responseProviderRef.get().get();
        WebContext webContext = new WebContext(httpServletRequest, httpServletResponse,
                servletContext, httpServletRequest.getLocale());
        webContext.setVariables(thymeleafViewable.getModels());
        try (Writer writer = new OutputStreamWriter(entityStream, StandardCharsets.UTF_8)) {
            templateEngine.process(thymeleafViewable.getTemplatePath(), webContext, writer);
        }
    }
}
