package minderupt.spectacular.spike;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;
import java.lang.annotation.Annotation;

import minderupt.spectacular.executor.euc.annotation.Flow;

/**
 * Created by IntelliJ IDEA.
 * User: michaeldowling
 * Date: Oct 22, 2009
 * Time: 10:01:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpikeClasspathScannerTest {


    @Test
    public void testUseSpringClasspathScanner() throws Exception {

        String basePackage = "minderupt/spectacular/spike";

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Flow.class));
        Set<BeanDefinition> beanSet = provider.findCandidateComponents(basePackage);
        
        assertTrue(beanSet.size() > 0);

        for(BeanDefinition beanDefinition : beanSet) {

            System.out.println("Bean Definition for:  " + beanDefinition.getBeanClassName());
            Object bean = Class.forName(beanDefinition.getBeanClassName()).newInstance();

            Annotation[] annotationList = bean.getClass().getAnnotations();
            for(Annotation a : annotationList) {
                System.out.println("  Annotation:  " + a.getClass().getName());
            }


        }


    }


}
