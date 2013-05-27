package com.thoughtworks.config;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/27/13
 * Time: 9:27 PM
 */
public class SessionMessageSource extends ResourceBundleMessageSource {

    private String basename;

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String brand = (String) attr.getAttribute("brand", RequestAttributes.SCOPE_SESSION);

        System.out.println("brand:" + brand);

        if (basename.equals(brand)) {
            return super.resolveCodeWithoutArguments(code, locale);
        }
        else
        {
            return getParentMessageSource().getMessage(code, null, locale);
        }

    }

    @Override
    public void setBasename(String basename) {
        this.basename = basename;
        super.setBasename(basename);
    }
}
