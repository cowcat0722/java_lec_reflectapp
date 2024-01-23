package ex03;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class App {

    // RequestMapping 어노테이션이 붙어있는 것을 확인
    public static void findUri(List<Object> instances, String path){

        for (Object instance : instances){
            Method[] methods = instance.getClass().getDeclaredMethods();

            for(Method method : methods){
                RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class);

                if(rm == null) continue;

                if(rm.uri().equals(path)){
                    try {
                        method.invoke(instance); // con.login();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Controller 어노테이션이 붙어있는거 확인
    public static List<Object> componentScan(String pkg) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageUrl = classLoader.getResource(pkg);

        File ex03 = new File(packageUrl.toURI());

        List<Object> instances = new ArrayList<>();

        for (File file : ex03.listFiles()){
            //System.out.println(file.getName());
            if(file.getName().endsWith(".class")){
                String className = pkg + "." + file.getName().replace(".class", "");
                //System.out.println(className);

                Class cls = Class.forName(className);
                if(cls.isAnnotationPresent(Controller.class)){
                    Object instance = cls.newInstance();
                    instances.add(instance); // UserController, BoardController
                }
            }
        }
        return instances;
    }

    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Object> instances = componentScan("ex03");
        findUri(instances, "/write");
    }
}