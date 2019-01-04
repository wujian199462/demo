package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class OnLineCountListener implements HttpSessionListener {
    private final static Logger LOG = LoggerFactory.getLogger(OnLineCountListener.class);

    private AtomicInteger onLineCount = new AtomicInteger(0);

    public void sessionCreated(HttpSessionEvent event) {
        LOG.info("创建Session");
        System.out.println("创建Session 了");
        event.getSession().getServletContext().setAttribute("onLineCount", onLineCount.incrementAndGet());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        LOG.info("销毁Session");
        System.out.println("销毁Session 了");
        event.getSession().getServletContext().setAttribute("onLineCount", onLineCount.decrementAndGet());
    }
}

