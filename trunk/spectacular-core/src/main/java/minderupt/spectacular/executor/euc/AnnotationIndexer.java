package minderupt.spectacular.executor.euc;

import minderupt.spectacular.executor.euc.annotation.EUC;
import minderupt.spectacular.executor.euc.annotation.Expectation;
import minderupt.spectacular.executor.euc.annotation.Flow;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: dowlinmi
 * Date: Oct 26, 2009
 * Time: 9:53:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class AnnotationIndexer {

    private static Logger LOGGER = Logger.getLogger(AnnotationIndexer.class);


    public void indexPackage(String basePackage, Map<Pattern, Executable> flowMap, Map<Pattern, Executable> expectationMap) {

        try {
            if (LOGGER.isInfoEnabled())
                LOGGER.info("Scanning classpath for annotated methods in package " + basePackage);
            Map<Pattern, Object> index = new HashMap<Pattern, Object>();
            ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
            provider.addIncludeFilter(new AnnotationTypeFilter(EUC.class));

            Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : beanDefinitionSet) {


                Class clazz = Class.forName(beanDefinition.getBeanClassName());
                if (LOGGER.isInfoEnabled()) LOGGER.info("Indexing class methods:  " + clazz.getName());
                Method[] methodList = clazz.getDeclaredMethods();
                for (Method method : methodList) {

                    // get annotations
                    Annotation[] annotationList = method.getDeclaredAnnotations();
                    for (Annotation ann : annotationList) {


                        if (ann instanceof Flow) {

                            Flow flow = (Flow) ann;
                            Pattern flowPattern = convertToPattern(flow.value());
                            Object instance = clazz.newInstance();
                            Executable exec = new Executable(method, instance);

                            flowMap.put(flowPattern, exec);

                        } else if (ann instanceof Expectation) {

                            Expectation exp = (Expectation) ann;
                            Pattern expectPattern = convertToPattern(exp.value());
                            Object instance = clazz.newInstance();
                            Executable exec = new Executable(method, instance);

                            expectationMap.put(expectPattern, exec);

                        }


                    }

                }


            }
        } catch (Exception e) {
            LOGGER.error("Unable to index package", e);
        }

    }


    private Pattern convertToPattern(String s) {

        // change variable pieces to regex groups
        // doing this with regex cuz im lazy
        Pattern p = Pattern.compile("\\$\\{(.*)\\}");
        Matcher m = p.matcher(s);
        String regexString = m.replaceAll("(.*?)");
        regexString = regexString.replaceAll(System.getProperty("line.separator"), "");

        Pattern realPattern = Pattern.compile(regexString);
        return (realPattern);

    }
}
