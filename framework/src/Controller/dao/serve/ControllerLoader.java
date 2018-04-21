package Controller.dao.serve;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ControllerLoader {



    public Map<String, Object> load() {                                 //定义一个返回值为
        Set<Class<?>> classSet = new HashSet<>();
        try {
            String packageName = "Controller";
            Enumeration<URL> resources = ControllerLoader.class.getClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                String protocol = resource.getProtocol();
                if (protocol.equals("file")) {
                    String packagePath = resource.getPath();
                    addClass(classSet, packagePath, packageName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> urlControllersMap = new HashMap<>();
        for (Class<?> clazz : classSet) {
            String className = clazz.getSimpleName();
            String controllerName = className.substring(0, className.lastIndexOf("Controller")).toLowerCase();
            try {
                urlControllersMap.put(controllerName, clazz.newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return urlControllersMap;
    }

    private void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(pathname -> (pathname.isFile() && pathname.getName().endsWith(".class")) || pathname.isDirectory());
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
                if (packageName != null && !packageName.equals("")) {
                    fileName = packageName + "." + fileName;
                }
                Class<?> clazz = loadClass(fileName, false);
                classSet.add(clazz);
            } else {
                String pathName = file.getName();
                if (pathName != null && !pathName.equals("")) {
                    packagePath = packagePath + "/" + pathName;
                    packageName = packageName + "." + pathName;
                }
                addClass(classSet, packagePath, packageName);
            }
        }
    }

    private Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className, isInitialized, getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }


    public static class Hello {

    //    static {
    //        System.out.println("hello");
    //    }


        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
