package com.example.rest.thymeleaf;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.AbstractTemplateProcessor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Jersey MVCでThymeleafを処理するクラス。
 * 下記のソースコードを参考に作りました。
 * @see org.glassfish.jersey.server.mvc.jsp.JspTemplateProcessor
 * @see http://bufferings.hatenablog.com/entry/2013/01/05/122307
 * @see https://github.com/backpaper0/sealion/blob/master/src/main/java/sealion/ui/ThymeleafProvider.java
 * @author tada
 */
@Provider
public class ThymeleafTemplateProcessor extends AbstractTemplateProcessor<String> {

    @Inject
    private javax.inject.Provider<Ref<HttpServletRequest>> requestProviderRef;
    @Inject
    private javax.inject.Provider<Ref<HttpServletResponse>> responseProviderRef;

    private TemplateEngine templateEngine;

    @Inject
    public ThymeleafTemplateProcessor(Configuration config, ServletContext servletContext) {
        super(config, servletContext, "html", "html");
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix((String) config.getProperty(MvcFeature.TEMPLATE_BASE_PATH));
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // setSuffix()は指定しない
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    protected String resolve(String templatePath, Reader reader) throws Exception {
        return templatePath;
    }

    @Override
    public void writeTo(String templateReference, Viewable viewable, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, OutputStream out) throws IOException {
        HttpServletRequest httpServletRequest = requestProviderRef.get().get();
        HttpServletResponse httpServletResponse = responseProviderRef.get().get();
        httpServletResponse.setCharacterEncoding("UTF-8");
        WebContext webContext = new WebContext(
                httpServletRequest, httpServletResponse, 
                super.getServletContext(), httpServletRequest.getLocale());
        Object model = viewable.getModel();
        if (model instanceof Map) {
            Map<String, Object> variables = (Map<String, Object>) model;
            webContext.setVariables(variables);
        } else {
            Map<String, Object> variables = new HashMap<>();
            variables.put("model", model);
            webContext.setVariables(variables);
        }
        try (Writer writer = new OutputStreamWriter(out)) {
            templateEngine.process(viewable.getTemplateName(), webContext, writer);
        }
    }

}
