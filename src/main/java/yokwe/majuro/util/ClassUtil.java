package yokwe.majuro.util;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;

public class ClassUtil {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ClassUtil.class);

	private static Map<String, Map<String, Long>> staticLongValueMap = new TreeMap<>();
	
	public static Long getStaticNumericValue(Class<?> clazz, String name) {
		String className = clazz.getCanonicalName();
		
		final Map<String, Long> map;
		
		if (staticLongValueMap.containsKey(className)) {
			map = staticLongValueMap.get(className);
		} else {
			map = new TreeMap<>();
			
			for(Field field: clazz.getDeclaredFields()) {
				try {
					int modifiers = field.getModifiers();
					// Ignore not static
					if (!Modifier.isStatic(modifiers)) continue;
					// Ignore not final
					if (!Modifier.isFinal(modifiers)) continue;
					
					String fieldName  = field.getName();
					String fieldType  = field.getType().getCanonicalName();
					Object fieldValue;
					fieldValue = field.get(null);
					
					if (fieldType.equals("int")) {
						long value = (int)fieldValue;
						map.put(fieldName, value);
					}
					if (fieldType.equals("long")) {
						long value = (long)fieldValue;
						map.put(fieldName, value);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					String exceptionName = e.getClass().getSimpleName();
					logger.error("{} {}", exceptionName, e);
					throw new UnexpectedException(exceptionName, e);
				}
			}
			staticLongValueMap.put(className, map);
		}
		if (map.containsKey(name)) {
			return map.get(name);
		} else {
			return null;
		}
	}

	public static Long getStaticNumericValue(String variableName) {
		final String className;
		final String name;
		
		{
			String[] names = variableName.split("\\.");
			name = names[names.length - 1];
			
			StringBuilder sb = new StringBuilder();
			sb.append(names[0]);
			for(int i = 1; i < names.length - 1; i++) {
				sb.append(".") ;
				sb.append(names[i]);
			}
			className = sb.toString();
		}
		
		try {
			Class<?> clazz = Class.forName(className);
			return getStaticNumericValue(clazz, name);
		} catch (ClassNotFoundException e) {
			logger.error("variableName {}", variableName);
			logger.error("className    {}", className);
			
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}

	
	public static class MethodReference {
	    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();
	    
	    public static <E> E toMethodReference(Class<E> target, Method method) {
			Method targetMethod;
	    	
	    	// Sanity check
	    	{
	    		if (!target.isInterface()) {
	    			logger.error("target is not interface");
	    			logger.error("  target {}", target.getName());
	    			throw new UnexpectedException("target is not interface");
	    		}
	    		if (!target.isAnnotationPresent(FunctionalInterface.class)) {
	    			logger.error("target is not FunctionalInterface");
	    			logger.error("  target {}", target.getName());
	    			throw new UnexpectedException("target is not FunctionalInterface");
	    		}
	    		{
					Method[] methods = target.getDeclaredMethods();
					if (methods.length == 1) {
						targetMethod = methods[0];
					} else {
		    			logger.error("target has more than one method");
		    			logger.error("  target {}", target.getName());
		    			for(int i = 0; i < methods.length; i++) {
			    			logger.error("  method {} {}", i, methods[i].getName());
		    			}
		    			throw new UnexpectedException("target has more than one method");
					}
	    		}
	    	}
	    	{
	    		int modifiers = method.getModifiers();
		    	if (!Modifier.isStatic(modifiers)) return null;
		    	if (!Modifier.isPublic(modifiers)) return null;
	    	}
	    	
	    	// build metodType
	    	MethodType methodType;
	    	{
	    		Class<?> returnType = targetMethod.getReturnType();
	    		Parameter[] parameters = targetMethod.getParameters();
	    		
	    		switch(parameters.length) {
	    		case 0:
	    			methodType = MethodType.methodType(returnType);
	    			break;
	    		case 1:
	    		{
	    			Class<?> parameterType0 = parameters[0].getClass();
	    			methodType = MethodType.methodType(returnType, parameterType0);
	    		}
	    			break;
	    		default:
	    		{
	    			Class<?> parameterType0 = parameters[0].getClass();
	    			Class<?>[] parameterTypes = new Class<?>[parameters.length - 1];
	    			for(int i = 1; i < parameters.length; i++) {
	    				parameterTypes[i - 1] = parameters[i].getClass();
	    			}
	    			methodType = MethodType.methodType(returnType, parameterType0, parameterTypes);
	    		}
	    			break;
	    		}
	    	}
	    	
	    	// build invokeType
			MethodType invokeType = MethodType.methodType(target);
			try {
				MethodHandle methodHandle = lookup.unreflect(method);
				if (methodHandle.type().equals(methodType)) {
					CallSite callSite = LambdaMetafactory.metafactory(lookup, targetMethod.getName(), invokeType, methodType, methodHandle, methodType);
					E e = (E) callSite.getTarget().invoke();
					return e;
				} else {
					return null;
				}
			} catch (Throwable e) {
				String exceptionName = e.getClass().getSimpleName();
				logger.error("{} {}", exceptionName, e);
				throw new UnexpectedException(exceptionName, e);
			}
	    }
	}
	
	// Get method reference Runnable from Method if applicable
    public static Runnable toRunnable(Method method) {
    	return MethodReference.toMethodReference(Runnable.class, method);
    }
    
	// class file enumeration using classLoader
    public static class FindClass {
        private final static char DOT = '.';
        private final static char SLASH = '/';
        private final static String CLASS_SUFFIX = ".class";

        public final static List<Class<?>> find(final String packageName) {
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            final String scannedPath = packageName.replace(DOT, SLASH);
            final Enumeration<URL> resources;
            try {
                resources = classLoader.getResources(scannedPath);
            } catch (IOException e) {
    			String exceptionName = e.getClass().getSimpleName();
    			logger.error("{} {}", exceptionName, e);
    			throw new UnexpectedException(exceptionName, e);
            }
            final List<Class<?>> classes = new LinkedList<Class<?>>();
            while (resources.hasMoreElements()) {
                final File file = new File(resources.nextElement().getFile());
                classes.addAll(findInFile(file, packageName));
            }
            return classes;
        }

        private final static List<Class<?>> findInFile(final File file, final String scannedPackage) {
            final List<Class<?>> classes = new LinkedList<Class<?>>();
            if (file.isDirectory()) {
                for (File nestedFile : file.listFiles()) {
                 	if (nestedFile.isDirectory()) {
                        classes.addAll(findInFile(nestedFile, scannedPackage + DOT + nestedFile.getName()));
                	} else {
                		classes.addAll(findInFile(nestedFile, scannedPackage));
                	}
                }
            } else if (file.getName().endsWith(CLASS_SUFFIX)) {
    	        final String resource = scannedPackage + DOT + file.getName();

    	        final int beginIndex = 0;
                final int endIndex = resource.length() - CLASS_SUFFIX.length();
                final String className = resource.substring(beginIndex, endIndex);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                	//logger.warn("e = {}", e);
                }
            }
            return classes;
        }
    }
    
    public final static List<Class<?>> findClass(final String packageName) {
    	return FindClass.find(packageName);
    }

}
